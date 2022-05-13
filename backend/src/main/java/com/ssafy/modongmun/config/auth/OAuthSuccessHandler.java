package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.jwt.JwtProperties;
import com.ssafy.modongmun.config.auth.jwt.JwtProvider;
import com.ssafy.modongmun.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // JWT creation
        final String JWT = jwtProvider.create(authentication.getName());

        log.info("Checking - - - - - - - - - - - - ");
        System.out.println("getPrincipal |\t" + authentication.getPrincipal());
        System.out.println("getAuthorities |\t" + authentication.getAuthorities());
        System.out.println("getName |\t" + authentication.getName());
        System.out.println("getCredentials |\t" + authentication.getCredentials());
        System.out.println("getDetails |\t" + authentication.getDetails());
        System.out.println("-----------------------------------------------");


//        // set JWT header
//        response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + JWT);
//
//        // send redirect
//        final String REDIRECT_URL = CookieUtils.getCookie(request, "redirect_url")
//                .map(Cookie::getValue)
//                .orElse(null);
//        log.info("Redirect to " + REDIRECT_URL);
//
//        response.sendRedirect(REDIRECT_URL);

        // send redirect
        final String REDIRECT_URL = CookieUtils.getCookie(request, "redirect_url")
                .map(Cookie::getValue)
                .orElse(null);
        System.out.println("REDIRECT_URL = " + REDIRECT_URL);

        final String targetUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + JWT)
                .build()
                .toUriString();
        System.out.println("targetUrl = " + targetUrl);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
