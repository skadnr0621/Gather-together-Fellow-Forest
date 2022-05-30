package com.ssafy.modongmun.school.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.dto.SchoolDto;
import com.ssafy.modongmun.school.schedule.dto.ScheduleDto;
import com.ssafy.modongmun.user.OAuthProvider;
import com.ssafy.modongmun.user.Role;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class ScheduleControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
//    private TestRestTemplate restTemplate;
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private UserRepository userRepository;


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

    @Before
    public void User_등록() throws Exception {
        for (School school : schoolRepository.findAll())
            System.out.println(school);
        // User 등록
        School elementarySchool = schoolRepository.findById(1L).orElse(null);
        assert elementarySchool != null;
        School middleSchool = schoolRepository.findById(2L).orElse(null);
        assert middleSchool != null;
        School highSchool = schoolRepository.findById(3L).orElse(null);
        assert highSchool != null;

        Long userNumber = 123456789L;
        String username = "홍길동";

        userRepository.save(User.builder()
                .userNumber(userNumber)
                .username(username)
                .elementarySchool(elementarySchool)
                .egYear(2001)
                .middleSchool(middleSchool)
                .mgYear(2002)
                .highSchool(highSchool)
                .hgYear(2003)
                .registerDate(LocalDateTime.now())
                .role(Role.USER)
                .provider(OAuthProvider.KAKAO)
                .build());
    }

    @After
    public void after() throws Exception {
        scheduleRepository.deleteAll();
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Schedule_등록() throws Exception {
        // given
        School school = schoolRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);

        String title = "title";
        String location = "location";
        String content = "content";

        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(7);

        ScheduleDto scheduleRegisterDto = ScheduleDto.builder()
                .schoolId(school.getSchoolId())
                .userId(user.getUserId())
                .title(title)
                .location(location)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        String url = "http://localhost:"+ port + "/api/schedule/schedules";

        // when
//        ResponseEntity<Void> response = restTemplate.postForEntity(url, scheduleRegisterDto, Void.class);
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(scheduleRegisterDto)))
                .andExpect(status().isOk());

        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Schedule savedSchedule = scheduleRepository.findById(1L).orElse(null);
        assert savedSchedule != null;

        assertThat(savedSchedule.getTitle()).isEqualTo(title);
        assertThat(savedSchedule.getLocation()).isEqualTo(location);
        assertThat(savedSchedule.getContent()).isEqualTo(content);

        assertThat(savedSchedule.getStartDate()).isEqualTo(startDate);
        assertThat(savedSchedule.getEndDate()).isEqualTo(endDate);
    }

}
