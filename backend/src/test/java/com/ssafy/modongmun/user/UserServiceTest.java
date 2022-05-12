package com.ssafy.modongmun.user;

import com.ssafy.modongmun.user.dto.SignupDto;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;


    @Before
    public void School_등록() throws Exception {
        // School 등록
        SchoolDto school1 = SchoolDto.builder()
                .code(9296064L)
                .name("월랑초등학교")
                .location("제주특별자치도")
                .build();
        SchoolDto school2 = SchoolDto.builder()
                .code(9296024L)
                .name("저청중학교")
                .location("제주특별자치도")
                .build();
        SchoolDto school3 = SchoolDto.builder()
                .code(9290066L)
                .name("제주과학고등학교")
                .location("제주특별자치도")
                .build();

        schoolRepository.save(School.toEntity(school1));
        schoolRepository.save(School.toEntity(school2));
        schoolRepository.save(School.toEntity(school3));
    }

    @After
    public void after() {
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    public void User_회원가입() throws Exception {
        // given
        School elementarySchool = schoolRepository.findByCode(9296064L).orElse(null);
        assert elementarySchool != null;
        School middleSchool = schoolRepository.findByCode(9296024L).orElse(null);
        assert middleSchool != null;
        School highSchool = schoolRepository.findByCode(9290066L).orElse(null);
        assert highSchool != null;

        int egYear = 2001;
        int mgYear = 2002;
        int hgYear = 2003;

        Long userNumber = 1234567890L;
        String username = "홍길동";

        LocalDateTime registerDate = LocalDateTime.now();

        SignupDto signupDto = SignupDto.builder()
                .userNumber(userNumber)
                .username(username)
                .elementarySchoolId(elementarySchool.getSchoolId())
                .egYear(egYear)
                .middleSchoolId(middleSchool.getSchoolId())
                .mgYear(mgYear)
                .highSchoolId(highSchool.getSchoolId())
                .hgYear(hgYear)
                .role(Role.USER)
                .build();

        // when
        userService.signup(signupDto);

        // then
        User savedUser = userRepository.findByUserNumber(userNumber).orElse(null);

        assertThat(savedUser.getUserNumber()).isEqualTo(userNumber);
        assertThat(savedUser.getUsername()).isEqualTo(username);
    }

}
