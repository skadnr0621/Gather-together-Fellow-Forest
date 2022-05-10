package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.BoardRepository;
import com.ssafy.modongmun.school.board.comment.dto.CommentRegisterDto;
import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
import com.ssafy.modongmun.school.dto.SchoolDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CommentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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
    }

    @Before
    public void User_등록() throws Exception {
        // User 등록
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
                .build());
    }

    @Before
    public void Board_등록() throws Exception{
        // given
        String title = "title";
        String content = "content";

        BoardRegisterDto boardRegisterDto = BoardRegisterDto.builder()
                .schoolId(1L)
                .userId(1L)
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:"+ port + "/api/board/posts";

        // when
        ResponseEntity<Void> response = restTemplate.postForEntity(url, boardRegisterDto, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Board savedBoard = boardRepository.findById(1L).orElse(null);
        assert savedBoard != null;

        assertThat(savedBoard.getTitle()).isEqualTo(title);
        assertThat(savedBoard.getContent()).isEqualTo(content);
    }

    @After
    public void after() throws Exception {
        commentRepository.deleteAll();
        boardRepository.deleteAll();
        userRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    public void Comment_등록() throws Exception{
        //given
        String content = "content";
        Long postId = 1L;

        CommentRegisterDto commentRegisterDto = CommentRegisterDto.builder()
                .postId(postId)
                .userId(1L)
                .content(content)
                .build();

        String url = "http://localhost:"+ port + "/api/board/posts/" + postId + "/comments";
        //when
        ResponseEntity<Void> response = restTemplate.postForEntity(url, commentRegisterDto, Void.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Comment savedComment = commentRepository.findById(1L).orElse(null);
        assert savedComment != null;

        assertThat(savedComment.getContent()).isEqualTo(content);
    }





}
