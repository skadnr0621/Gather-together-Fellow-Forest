package com.ssafy.modongmun.user;

import com.ssafy.modongmun.user.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupDto signupDto) {
        User user = User.toEntity(signupDto);
        userRepository.save(user);
    }
}
