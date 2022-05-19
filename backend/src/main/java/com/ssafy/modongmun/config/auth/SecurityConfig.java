package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final OAuthFailureHandler oAuthFailureHandler;

    private final CorsFilter corsFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* h2-console 사용 설정 */
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                /* Session */
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                /* URL 별 권한 관리 */
                .authorizeRequests()
                .antMatchers("/oauth/authentication/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()

                /* OAuth 2 로그인 기능 설정 */
                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                // OAuth 2 로그인 성공 이후, 사용자 정보 획득 설정
                .userInfoEndpoint()
                // 소셜 로그인 성공 후 후속 조치 구현체 등록
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuthSuccessHandler)
                .failureHandler(oAuthFailureHandler);

        // Custom Filters -------------
        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilter(corsFilter);
    }
}
