package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.request.LogoutRequest;
import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.InvalidToken;
import com.example.demo.entity.User;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.InvalidTokenRepository;
import com.example.demo.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;
    @NonFinal
    @Value("${jwt.VALID_DURATION}")
    protected long VALID_DURATION;
    @NonFinal
    @Value("${jwt.REFRESHABLE_DURATION}")
    protected long REFRESHABLE_DURATION;


    private final InvalidTokenRepository invalidTokenRepository;

    public AuthenticationResponse authenticated(AuthenticationRequest request){



        User user = userRepository.findByUserName(request.getUsername()).orElseThrow(()->new ApplicationException(ErrorCode.USER_NOT_EXIST));
        boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getPassWord());
        if(!authenticated){
            throw new ApplicationException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .token(token)
                .build();

    }

 // 1 JWT gồm Header, Payload, Signature
    private String generateToken(User user)  {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .claim("userID",user.getId())
                .issuer("phucnguyen")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .claim("scope",buildScope(user)) // nhận diện role qua thuộc tính scope
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);

      try{
          jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
          return jwsObject.serialize();
      } catch (JOSEException e) {
          System.out.println("cannot create token"+e);
          throw new RuntimeException(e);
      }

    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
          user.getRoles().forEach(role->{
              stringJoiner.add("ROLE_"+role.getName());
              if(!CollectionUtils.isEmpty(role.getPermissions()))
              role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
          });
        return stringJoiner.toString();
    }
    public IntrospectResponse introspectToken(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        try {
            var jwtToken = verifyToken(token,false);
        } catch (ApplicationException e) {
            return IntrospectResponse.builder().valid(false).build();
        }

        return IntrospectResponse.builder().valid(true).build();

    }
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
           var signToken = verifyToken(request.getToken(),true);
           String jit = signToken.getJWTClaimsSet().getJWTID();
           Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidToken invalidToken = InvalidToken.builder().id(jit).expityTime(expiryTime).build();
        invalidTokenRepository.save(invalidToken);

    }
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
         var token =  verifyToken(request.getToken(),true);

         var invalidToken = InvalidToken.builder()
                 .id(token.getJWTClaimsSet().getJWTID())
                 .expityTime(token.getJWTClaimsSet().getExpirationTime())
                 .build();
        invalidTokenRepository.save(invalidToken);

         User user = userRepository
                 .findById(String.valueOf(token.getJWTClaimsSet().getClaim("userID")))
                 .orElseThrow(()->new ApplicationException(ErrorCode.UNAUTHENTICATED));

         return AuthenticationResponse.builder().token(generateToken(user)).authenticated(true).build();

    }

    private SignedJWT verifyToken(String token,boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean valid = signedJWT.verify(verifier);

        Date expityTime = (isRefresh)? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION,ChronoUnit.SECONDS).toEpochMilli()) : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean IsValid = valid && expityTime.after(new Date());
        if(!IsValid) throw new ApplicationException(ErrorCode.UNAUTHENTICATED);

        //check logouted token
       if(invalidTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
           throw new ApplicationException(ErrorCode.UNAUTHENTICATED);}
        return signedJWT;

    }



}
