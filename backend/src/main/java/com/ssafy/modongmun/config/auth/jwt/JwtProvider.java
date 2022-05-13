package com.ssafy.modongmun.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    public String create(String email) {
        return JWT.create()
                .withSubject("Modongmun's JWT")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

}
