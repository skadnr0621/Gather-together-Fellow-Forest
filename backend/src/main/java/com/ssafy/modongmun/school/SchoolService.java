package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public void register(SchoolDto schoolDto) {
        School school = School.toEntity(schoolDto);
        schoolRepository.save(school);
    }
}
