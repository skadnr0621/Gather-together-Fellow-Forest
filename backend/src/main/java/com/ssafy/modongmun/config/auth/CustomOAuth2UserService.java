package com.ssafy.modongmun.config.auth;

import com.ssafy.modongmun.config.auth.dto.OAuthAttributes;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // registrationId는 현재 로그인을 진행 중인 서비스를 구분하는 코드입니다. (구글 로그인, 네이버 로그인 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // userNameAttributeName은 OAuth2 로그인 진행 시, Key가 되는 필드값을 의미합니다. (Primary Key와 같은 의미)
        // 구글의 경우, 기본 코드를 지원합니다. (sub)
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuthAttributes는 OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담는 클래스입니다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        log.info("OAuth2 User attributes are as follows ********************");
        System.out.println("\t[ Key ]  :  [ Value ]");
        System.out.println(":: -------------------------------------------------- ::");
        for (String key : attributes.getAttributes().keySet())
            System.out.printf(">> [ %s ] :  [ %s ]%n", key, attributes.getAttributes().get(key));
        System.out.println(":: -------------------------------------------------- ::");

        User user = saveOrUpdate(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUserNumber(attributes.getUserNumber())
//                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
