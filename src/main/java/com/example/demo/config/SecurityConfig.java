package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;



import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS ={"/users/create","/auth/token","/auth/introspect"};
   @Value("${jwt.signerKey}")
    private String SIGNER_KEY;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request->request
                .requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()
                 .anyRequest().authenticated());
        //Đăng kí auth bằng jwt token
        httpSecurity.oauth2ResourceServer(oAuth2 ->
                oAuth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(),"HS512");
        return  NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    };

}
