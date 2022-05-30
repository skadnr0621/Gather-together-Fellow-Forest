package com.ssafy.modongmun.config.auth.dto;

import com.ssafy.modongmun.user.OAuthProvider;
import com.ssafy.modongmun.user.Role;
import com.ssafy.modongmun.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Slf4j
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private Long userNumber;
    private String email;
    private String username;
    private OAuthProvider provider;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes,
            String nameAttributeKey,
            Long userNumber,
            String email,
            String username,
            OAuthProvider provider
    ) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userNumber = userNumber;
        this.email = email;
        this.username = username;
        this.provider = provider;
    }

    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes) {

        log.info("OAuthAttributes of ---------------");
        log.info("registration ID / userNameAttributeName : {} / {}", registrationId, userNameAttributeName);
        for (String key : attributes.keySet())
            System.out.printf(">> [ %s ] : %s%n", key, attributes.get(key));
        System.out.println("----------------------------------------");

        if ("naver".equals(registrationId))
            return ofNaver("id", attributes);
        else if ("kakao".equals(registrationId))
//            return ofKakao("email", attributes);
            return ofKakao(userNameAttributeName, attributes);

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .email((String)response.get("email"))
                .username((String)response.get("name"))
                .provider(OAuthProvider.NAVER)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, String> profile = (Map<String, String>)response.get("profile");

        return OAuthAttributes.builder()
                .userNumber((Long)attributes.get(userNameAttributeName))
                .email((String)response.get("email"))   // User 회원가입 및 정보 갱신에 사용될 정보입니다.
                .username(profile.get("nickname"))      // User 회원가입 및 정보 갱신에 사용될 정보입니다.
                .provider(OAuthProvider.KAKAO)          // User 회원가입 및 정보 갱신에 사용될 정보입니다.
                .attributes(attributes)                     // OAuth2User가 실제로 갖게 되는 정보입니다.
                .nameAttributeKey(userNameAttributeName)    // OAuth2User가 실제로 갖게 되는 정보입니다.
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .userNumber((Long)attributes.get("userNumber"))
                .email((String)attributes.get("email"))
                .username((String)attributes.get("name"))
                .provider(OAuthProvider.GOOGLE)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userNumber(userNumber)
                .email(email)
                .username(username)
                .role(Role.USER)
                .provider(provider)
                .build();
    }

}
