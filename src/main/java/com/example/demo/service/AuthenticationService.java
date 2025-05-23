package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;
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
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope",buildScope(user)) // nhận diện role qua thuộc tính scope
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
            user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
    public IntrospectResponse introspectToken(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

       boolean valid = signedJWT.verify(verifier);

       Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

       boolean verifyResult = valid && expityTime.after(new Date());

       return IntrospectResponse.builder().valid(verifyResult).build();

    }



}
