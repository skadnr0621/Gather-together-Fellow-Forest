package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.jwt.JwtProperties;
import com.ssafy.modongmun.config.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.execchain.RedirectExec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // JWT creation
        final String JWT = jwtService.generate(authentication.getName());

        log.info("Checking - - - - - - - - - - - - ");
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println("-----------------------------------------------");


        // set JWT header
        response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + JWT);

        // send redirect
        String REDIRECT_URL = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("redirect_url")) {
                REDIRECT_URL = cookie.getValue();
                break;
            }
        }
        log.info("Redirect to " + REDIRECT_URL);

//        response.sendRedirect(REDIRECT_URL);
        response.sendRedirect("http://localhost:8081");
    }

}
