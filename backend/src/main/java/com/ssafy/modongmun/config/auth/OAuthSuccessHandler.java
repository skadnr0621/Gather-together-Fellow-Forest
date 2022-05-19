package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.jwt.JwtProperties;
import com.ssafy.modongmun.config.auth.jwt.JwtProvider;
import com.ssafy.modongmun.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Check authentication information");
        System.out.println("Checking - - - - - - - - - - - - ");
        System.out.println("getPrincipal >>>\t" + authentication.getPrincipal());
        System.out.println("getAuthorities >>>\t" + authentication.getAuthorities());
        System.out.println("getName >>>\t" + authentication.getName());
        System.out.println("getCredentials >>>\t" + authentication.getCredentials());
        System.out.println("getDetails >>>\t" + authentication.getDetails());
        System.out.println("-----------------------------------------------");

        // JWT creation
        final String JWT = jwtProvider.create((OAuth2User)authentication.getPrincipal());

        // back to service with JWT
        final String REDIRECT_URL = CookieUtils.getCookie(request, "redirect_url")
                .map(Cookie::getValue)
                .orElse("/");
        System.out.println("REDIRECT_URL = " + REDIRECT_URL);
//        if (REDIRECT_URL == null)
//            return;

//        final String targetUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
//                .queryParam(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + JWT)
//                .build()
//                .toUriString();
//        System.out.println("targetUrl = " + targetUrl);

        // put JWT in response cookie
        Cookie cookie = new Cookie(JwtProperties.HEADER_STRING, JWT);
        cookie.setPath("/");
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        clearAuthenticationAttributes(request, response);
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        getRedirectStrategy().sendRedirect(request, response, REDIRECT_URL);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
