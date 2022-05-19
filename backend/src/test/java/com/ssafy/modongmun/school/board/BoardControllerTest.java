package com.ssafy.modongmun.school.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.dto.PostDto;
import com.ssafy.modongmun.school.dto.SchoolDto;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class BoardControllerTest {

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
    private BoardRepository boardRepository;
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

    @Before
    public void User_등록() throws Exception {
        // User 등록
        School elementarySchool = schoolRepository.findByCode(9296064L).orElse(null);
        assert elementarySchool != null;
        School middleSchool = schoolRepository.findByCode(9296024L).orElse(null);
        assert middleSchool != null;
        School highSchool = schoolRepository.findByCode(9290066L).orElse(null);
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
        boardRepository.deleteAll();
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Board_등록() throws Exception {
        // given
        User user = userRepository.findByUserNumber(123456789L).orElse(null);
        assert user != null;
        School enteredSchool = schoolRepository.findById(user.getElementarySchool().getSchoolId()).orElse(null);
        assert enteredSchool != null;

        String title = "title";
        String content = "content";

        PostDto postDto = PostDto.builder()
                .schoolId(enteredSchool.getSchoolId())
                .userId(user.getUserId())
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:"+ port + "/api/board/posts";

        // when
//        ResponseEntity<PostDto> response = restTemplate.postForEntity(url, postDto, PostDto.class);
        MockHttpServletResponse response = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");

        // then
        PostDto savedPostDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(response.getContentAsByteArray(), PostDto.class);
        Board savedBoard = boardRepository.findById(savedPostDto.getPostId()).orElse(null);
        assert savedBoard != null;

        assertThat(savedBoard.getTitle()).isEqualTo(title);
        assertThat(savedBoard.getContent()).isEqualTo(content);

//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        PostDto savedPostDto = response.getBody();
//        Board savedBoard = boardRepository.findById(savedPostDto.getPostId()).orElse(null);
//        assert savedBoard != null;
//
//        assertThat(savedBoard.getTitle()).isEqualTo(title);
//        assertThat(savedBoard.getContent()).isEqualTo(content);
    }

}
