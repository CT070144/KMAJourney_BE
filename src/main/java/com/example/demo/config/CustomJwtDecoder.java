package com.example.demo.config;

import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder=null;
    @Override
    public Jwt decode(String token) throws JwtException {
       try{
           var response = authenticationService.introspectToken(IntrospectRequest.builder().token(token).build());
           if(!response.isValid())
               throw new ApplicationException(ErrorCode.UNAUTHENTICATED);
       } catch (ParseException|JOSEException e) {
           throw new ApplicationException(ErrorCode.UNCATEGORIZED_EXCEPTION);
       }
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
