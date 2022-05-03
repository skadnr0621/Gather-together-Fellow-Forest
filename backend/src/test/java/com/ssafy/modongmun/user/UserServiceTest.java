package com.ssafy.modongmun.user;

import com.ssafy.modongmun.user.dto.SignupDto;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@RequiredArgsConstructor
public class UserServiceTest {

//    @LocalServerPort
//    private int port;

//    private final UserService userService;
//    private final UserRepository userRepository;
//
//    private final SchoolRepository schoolRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;


    @After
    public void after() {
        userRepository.deleteAll();
    }

    @Test
    public void User_회원가입() throws Exception {
        // given
        Long code = 7010057L;
        String name = "가락고등학교";
        String location = "서울특별시";

        SchoolDto schoolDto = SchoolDto.builder()
//                .SD_SCHUL_CODE(code)
//                .SCHUL_NM(name)
//                .LCTN_SC_NM(location)
                .code(code)
                .name(name)
                .location(location)
                .build();
        schoolRepository.save(School.toEntity(schoolDto));

        School elementary = schoolRepository.findById(1L).orElse(null);
        assertThat(elementary).isNotEqualTo(null);

        SchoolDto elementaryDto = SchoolDto.toDto(elementary);
        School middle = schoolRepository.findById(1L).orElse(null);
        SchoolDto middleDto = SchoolDto.toDto(middle);
        School high = schoolRepository.findById(1L).orElse(null);
        SchoolDto highDto = SchoolDto.toDto(high);

        int egYear = 2001;
        int mgYear = 2002;
        int hgYear = 2003;

        Long userNumber = 1234567890L;
        String username = "홍길동";

        LocalDateTime registerDate = LocalDateTime.now();

        SignupDto signupDto = SignupDto.builder()
                .userNumber(userNumber)
                .username(username)
                .elementarySchoolDto(elementaryDto)
                .egYear(egYear)
                .middleSchoolDto(middleDto)
                .mgYear(mgYear)
                .highSchoolDto(highDto)
                .hgYear(hgYear)
                .build();

        // when
        userService.signup(signupDto);

        // then
        User savedUser = userRepository.findByUserNumber(userNumber).orElse(null);

        assertThat(savedUser.getUserNumber()).isEqualTo(userNumber);
        assertThat(savedUser.getUsername()).isEqualTo(username);
    }

}
