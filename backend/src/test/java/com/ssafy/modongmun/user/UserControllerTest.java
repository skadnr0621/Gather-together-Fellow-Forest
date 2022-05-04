package com.ssafy.modongmun.user;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.dto.SchoolDto;
import com.ssafy.modongmun.user.dto.SignupDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;


    @Before
    public void School_등록() throws Exception {
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
    public void after() throws Exception {
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    public void User_회원가입() throws Exception {
        // given
        School elementarySchool = schoolRepository.findById(1L).orElse(null);
        System.out.println("elementarySchool = " + elementarySchool);
        assert elementarySchool != null;
        School middleSchool = schoolRepository.findById(2L).orElse(null);
        System.out.println("middleSchool = " + middleSchool);
        assert middleSchool != null;
        School highSchool = schoolRepository.findById(3L).orElse(null);
        System.out.println("highSchool = " + highSchool);
        assert highSchool != null;

        Long userNumber = 123456789L;
        String username = "홍길동";

        SignupDto signupDto = SignupDto.builder()
                .userNumber(userNumber)
                .username(username)
                .elementarySchoolDto(SchoolDto.toDto(elementarySchool))
                .egYear(2001)
                .middleSchoolDto(SchoolDto.toDto(middleSchool))
                .mgYear(2002)
                .highSchoolDto(SchoolDto.toDto(highSchool))
                .hgYear(2003)
                .build();

        String url = "http://localhost:"+port + "/api/signup";

        // when
        ResponseEntity<Void> response = restTemplate.postForEntity(url, signupDto, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        User savedUser = userRepository.findById(1L).orElse(null);
        assert savedUser != null;

        assertThat(savedUser.getUserNumber()).isEqualTo(userNumber);
        assertThat(savedUser.getUsername()).isEqualTo(username);

//        System.out.println("초등학교 : " + savedUser.getElementarySchool());
//        System.out.println("중학교 : " + savedUser.getMiddleSchool());
//        System.out.println("고등학교 : " + savedUser.getHighSchool());

        assertThat(savedUser.getEgYear()).isEqualTo(2001);
        assertThat(savedUser.getMgYear()).isEqualTo(2002);
        assertThat(savedUser.getHgYear()).isEqualTo(2003);
    }

}
