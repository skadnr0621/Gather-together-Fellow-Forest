package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import org.junit.After;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SchoolControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SchoolRepository schoolRepository;

    @After
    public void after() throws Exception {
        schoolRepository.deleteAll();
    }

    @Test
    public void School_Register() throws Exception {
        // given
        Long code = 98798459998789L;
        String name = "싸피고등학교";
        String location = "대한민국";

        SchoolDto schoolDto = SchoolDto.builder()
                .code(code)
                .name(name)
                .location(location)
                .build();

        String url = "http://localhost:" + port + "/api/school/schools";

        // when
        ResponseEntity<Void> response = restTemplate.postForEntity(url, schoolDto, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        School savedSchool = schoolRepository.findByCode(code).orElse(null);
        assert savedSchool != null;

        assertThat(savedSchool.getName()).isEqualTo(name);
        assertThat(savedSchool.getLocation()).isEqualTo(location);
    }

}
