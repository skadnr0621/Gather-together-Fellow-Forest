package com.ssafy.modongmun.config.auth.dto;

import com.ssafy.modongmun.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String username;

    public SessionUser(User user) {
        this.username = user.getUsername();
    }

}
