package com.ssafy.modongmun.config.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/oauth-login-success")
    public Object loginSuccessful() {
        return "success";
    }

}
