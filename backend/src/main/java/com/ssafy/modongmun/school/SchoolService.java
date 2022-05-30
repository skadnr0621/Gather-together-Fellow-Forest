package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public void register(SchoolDto schoolDto) {
        School school = School.toEntity(schoolDto);
        schoolRepository.save(school);
    }

    public List<SchoolDto> searchSchool(String keyword) {
        List<School> schoolList = schoolRepository.findByNameContaining(keyword);
        List<SchoolDto> schoolDtoList = new ArrayList<>();

        for(School school : schoolList){
            SchoolDto schoolDto = SchoolDto.toDto(school);
            schoolDtoList.add(schoolDto);
            System.out.println(schoolDto.getName());
        }
        return schoolDtoList;
    }
}
