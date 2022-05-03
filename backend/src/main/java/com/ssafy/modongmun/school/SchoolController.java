package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/school/schools")
    public ResponseEntity<?> register(@RequestBody SchoolDto schoolDto) {
        System.out.println("Got request :: " + schoolDto);
        schoolService.register(schoolDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
