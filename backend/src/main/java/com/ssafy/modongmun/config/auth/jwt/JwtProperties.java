package com.ssafy.modongmun.config.auth.jwt;

public interface JwtProperties {

    String SECRET = "Gather_Around_-_Dongmun's_Forest (Alumni_Crossing:_New_Horizons)";

    /*** Constant Fields ***/
    int MINUTE = 60, HOUR = 60 * MINUTE;
    int DAY = 24 * HOUR, WEEK = 7 * DAY, MONTH = 30 * DAY, YEAR = 365 * DAY;

    /*** JWT expiration time configuration ***/
    int hours = 0;
    int minutes = 15;
    int seconds = 15;

    int EXPIRATION_TIME = (HOUR * hours + MINUTE * minutes + seconds) * 1000;

    /*** Refresh token expiration time configuration ***/
    int years = 0;
    int months = 0;
    int weeks = 2;
    int days = 0;

    int REFRESH_EXPIRATION_TIME = (YEAR * years + MONTH * months + WEEK * weeks + DAY * days) * 1000;


    /*** Token information ***/
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    String REFRESH_HEADER_STRING = "Refresh-Token";

}
