package com.ssafy.modongmun.school.dto;

import com.ssafy.modongmun.school.School;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class SchoolDto {

    private Long schoolId;

    private Long code;
    private String name;
    private String location;
    private String keyword;

    public static SchoolDto toDto(School school) {
        return SchoolDto.builder()
                .schoolId(school.getSchoolId())
                .code(school.getCode())
                .name(school.getName())
                .location(school.getLocation())
                .build();
    }

}
