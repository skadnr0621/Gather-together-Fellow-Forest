package com.ssafy.modongmun.config.auth.jwt;

import com.ssafy.modongmun.config.auth.UserPrincipalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserPrincipalService userPrincipalService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Authentication filter --- " + request.getRequestURI());

        // Pull out Authorization token from the request header
        final String authToken = request.getHeader("Authorization");
        log.info("Got Authorization header : " + authToken);

        if (authToken != null && authToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
            // remove prefix
            final String JWT = authToken.replace(JwtProperties.TOKEN_PREFIX, "");

            // Set authentication in Spring Security Session
            try {
                // 0. verify JWT first
                jwtProvider.verify(JWT);
                // 1. get user ID (it's email for now)
                final String claim = "email";
                final String email = jwtProvider.getClaim(JWT, claim);
                log.info("parsed claim [{}] : {}", claim, email);

                // 2. create UserDetails
                UserDetails userDetails = userPrincipalService.loadUserByUsername(email);
                // 3. create authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                log.info("Authentication created :: " + authentication);

                // set authentication
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e) {
                log.error("Authentication failed !!!");
                e.printStackTrace();
            }
        }
        else { // empty Authentication Header or invalid prefix (not our token))
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        // next
        filterChain.doFilter(request, response);
    }
}
