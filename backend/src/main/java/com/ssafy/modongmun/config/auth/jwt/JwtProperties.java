package com.ssafy.modongmun.config.auth.jwt;

public interface JwtProperties {

    String SECRET = "Gather_Around_-_Dongmun's_Forest (Alumni_Crossing:_New_Horizons)";

    int hour = 1;
    int minute = 30;
    int second = 0;
    int EXPIRATION_TIME = (hour * 3600 + minute * 60 + second) * 1000;

    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

}
