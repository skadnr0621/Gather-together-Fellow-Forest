package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.jwt.JwtProperties;
import com.ssafy.modongmun.config.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final JwtProvider jwtProvider;

    @PostMapping("/auth/token/refresh")
    public ResponseEntity<String> refreshJWT(@RequestHeader(JwtProperties.HEADER_STRING) String oldToken,
                                             @RequestHeader(JwtProperties.REFRESH_HEADER_STRING) String refreshToken) {

        String freshJWT = jwtProvider.refresh(oldToken, refreshToken);
        return new ResponseEntity<>(freshJWT, HttpStatus.OK);
    }

}
