package com.ssafy.modongmun.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ssafy.modongmun.config.auth.UserPrincipal;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    private final UserRepository userRepository;


    /**
     * 새로운 JWT를 발급합니다.
     * @param oAuth2User JWT에 기입될 사용자 정보 객체 (Authentication으로부터 기인함)
     * @return String : newly generated JWT
     */
    public String create(OAuth2User oAuth2User) {
        User user = userRepository.findByUserNumber(Long.parseLong(oAuth2User.getName())).orElse(null);
        if (user == null)
            return null;

        log.info("New JWT created - Due: " + new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME));
        return JWT.create()
                .withSubject("Modongmun's JWT")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getUserId())
                .withClaim("email", user.getEmail())
                .withClaim("provider", user.getProvider().getProvider_en())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    /**
     * JWT의 claim을 조회합니다.
     * @param token Claim을 조회할 대상 JWT
     * @param claim 조회할 Claim
     * @return 조회된 Claim(String) 또는 null (null claim)
     */
    public String getClaim(String token, String claim) {
        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                .verify(token)
                .getClaim(claim)
                .asString();
    }

    /**
     * JWT를 검증합니다.
     * @param token 검증할 JWT
     * @return 검증 성공 여부
     */
    public boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                    .verify(token);
            return true;
        }
        catch (AlgorithmMismatchException e) {
            log.error("JWT signing algorithm is not matched !");
        }
        catch (SignatureVerificationException e) {
            log.error("JWT secret is invalid !");
        }
        catch (TokenExpiredException e) {
            log.error("JWT expired !");
        }
        catch (JWTVerificationException e) {
            log.error("JWT verification failed !");
        }
        return false;
    }

    /**
     * 만료된 JWT를 갱신합니다(재발급)
     * @param oldToken
     * @param refreshToken
     * @return String : reissued JWT
     */
    public String refresh(String oldToken, String refreshToken) { // ref. https://kukekyakya.tistory.com/entry/Spring-boot-access-token-refresh-token-%EB%B0%9C%EA%B8%89%EB%B0%9B%EA%B8%B0jwt
        // 1. Verify refresh token

        // 2. Compare incoming <> actual saved
        // 2-1. extract user info from old token
        String userNumber = getClaim(oldToken, "userNumber");
//        User user = userRepository.findByUserNumber(userNumber).orElse(null);
//        if (user == null)
//            return null;


        // 2-2. seek refresh token of the user

        // 3. Reissue JWT
//        return create(user);
        return null;
    }

}
