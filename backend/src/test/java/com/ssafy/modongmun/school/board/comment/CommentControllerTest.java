package com.ssafy.modongmun.school.board.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.BoardRepository;
import com.ssafy.modongmun.school.board.comment.dto.CommentDto;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class CommentControllerTest {
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
    private CommentRepository commentRepository;
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

        User_등록();
    }

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

        Board_등록();
    }

    public void Board_등록() throws Exception{
        // given
        User user = userRepository.findByUserNumber(123456789L).orElse(null);
        assert user != null;
        School enteredSchool = schoolRepository.findById(user.getElementarySchool().getSchoolId()).orElse(null);
        assert enteredSchool != null;

        String title = "title";
        String content = "content";

        boardRepository.save(Board.builder()
                .school(enteredSchool)
                .user(user)
                .title(title)
                .content(content)
                .build());
    }

    @After
    public void after() throws Exception {
        commentRepository.deleteAll();
        boardRepository.deleteAll();
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Comment_등록() throws Exception{
        // given
        User user = userRepository.findByUserNumber(123456789L).orElse(null);
        assert user != null;
        School enteredSchool = schoolRepository.findById(user.getElementarySchool().getSchoolId()).orElse(null);
        assert enteredSchool != null;

        List<Board> postList = boardRepository.findByUser(user);
        assert postList.size() > 0;
        Board post = postList.get(0);

        String content = "content";

        CommentDto commentDto = CommentDto.builder()
                .postId(post.getPostId())
                .userId(user.getUserId())
                .content(content)
                .build();

//        String url = "http://localhost:"+ port + "/api/board/posts/" + post.getPostId() + "/comments";
        String url = String.format("http://localhost:%d/api/board/posts/%d/comments", port, post.getPostId());

        // when
//        ResponseEntity<Void> response = restTemplate.postForEntity(url, commentDto, Void.class);
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(commentDto)))
                .andExpect(status().isOk());

        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Comment savedComment = commentRepository.findById(1L).orElse(null);
        assert savedComment != null;

        assertThat(savedComment.getContent()).isEqualTo(content);
    }





}
