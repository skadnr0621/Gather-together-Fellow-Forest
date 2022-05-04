package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SchoolControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @After
    public void after() {
//        schoolRe
    }

    @Test
    public void School_Register() throws Exception {
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

        String url = "http://localhost:" + 8080 + "/api/school/schools";

        System.out.println("Before request :: " + schoolDto);
        ResponseEntity<String> response = restTemplate.postForEntity(url, schoolDto, String.class);

    }

}
