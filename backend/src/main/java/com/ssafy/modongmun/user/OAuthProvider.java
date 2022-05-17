package com.ssafy.modongmun.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuthProvider {

    GOOGLE("GOOGLE", "구글"),
    NAVER("NAVER", "네이버"),
    KAKAO("KAKAO", "카카오");

    private final String provider_en;
    private final String provider_kr;

}
