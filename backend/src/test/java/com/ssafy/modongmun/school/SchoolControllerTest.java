package com.ssafy.modongmun.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.modongmun.school.dto.SchoolDto;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class SchoolControllerTest {

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
    private SchoolRepository schoolRepository;

    @After
    public void after() throws Exception {
        schoolRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
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
//        ResponseEntity<Void> response = restTemplate.postForEntity(url, schoolDto, Void.class);
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(schoolDto)))
                .andExpect(status().isOk());

        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        School savedSchool = schoolRepository.findByCode(code).orElse(null);
        assert savedSchool != null;

        assertThat(savedSchool.getName()).isEqualTo(name);
        assertThat(savedSchool.getLocation()).isEqualTo(location);
    }

}
