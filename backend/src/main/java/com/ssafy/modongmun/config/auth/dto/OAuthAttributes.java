package com.ssafy.modongmun.config.auth.dto;

import com.ssafy.modongmun.user.OAuthProvider;
import com.ssafy.modongmun.user.Role;
import com.ssafy.modongmun.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
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

        if ("naver".equals(registrationId))
            return ofNaver("id", attributes);
        else if ("kakao".equals(registrationId))
            return ofKakao("email", attributes);

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
//                .userNumber(Long.valueOf((String)response.get("id")))
                .email((String)response.get("email"))
                .username(profile.get("nickname"))
                .provider(OAuthProvider.KAKAO)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
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
