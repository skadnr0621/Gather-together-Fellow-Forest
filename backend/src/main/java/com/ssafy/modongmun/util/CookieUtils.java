package com.ssafy.modongmun.util;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;

public class CookieUtils {

    /**
     * request로부터 name에 해당하는 cookie를 찾아 반환합니다.
     * @param request
     * @param name
     * @return Optional.of 또는 Optional.empty
     */
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name))
                return Optional.of(cookie);
        }
        return Optional.empty();
    }

    /**
     * response에 name:value로 maxAge만큼 유효한 cookie를 추가합니다.
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * request에서 name과 일치하는 cookie를 찾아 maxAge를 0으로 설정하고, response에 담습니다.
     * @param request
     * @param response
     * @param name
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
    }

    /**
     * obj를 Base64 encoding하여 return합니다.
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    /**
     * cookie의 value를 Base64 decode한 뒤, cls로 casting하여 return합니다.
     * @param cookie
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())
                ));
    }

}
