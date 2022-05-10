package com.ssafy.modongmun.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* h2-console 사용 설정 */
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                /* URL 별 권한 관리 */
                .authorizeRequests()
//                .antMatchers("/", "/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()

                /* OAuth 2 로그인 기능 설정 */
                .oauth2Login()
                // OAuth 2 로그인 성공 이후, 사용자 정보 획득 설정
                .userInfoEndpoint()
                // 소셜 로그인 성공 후 후속 조치 구현체 등록
                .userService(customOAuth2UserService);

    }
}
