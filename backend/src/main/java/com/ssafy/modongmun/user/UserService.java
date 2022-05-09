package com.ssafy.modongmun.user;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.user.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public void signup(SignupDto signupDto) {
//        User user = User.toEntity(signupDto);
//        userRepository.save(user);

        School elementarySchool = schoolRepository.findById(signupDto.getElementarySchoolId()).orElse(null);
        School middleSchool = schoolRepository.findById(signupDto.getMiddleSchoolId()).orElse(null);
        School highSchool = schoolRepository.findById(signupDto.getHighSchoolId()).orElse(null);

        userRepository.save(User.builder()
                .userNumber(signupDto.getUserNumber())
                .username(signupDto.getUsername())
                .elementarySchool(elementarySchool)
                .egYear(signupDto.getEgYear())
                .middleSchool(middleSchool)
                .mgYear(signupDto.getMgYear())
                .highSchool(highSchool)
                .hgYear(signupDto.getHgYear())
                .registerDate(LocalDateTime.now())
                .build());
    }
}
