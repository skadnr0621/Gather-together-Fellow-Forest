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

//    @Builder
//    public SchoolDto(Long code, String name, String location) {
////        this.SD_SCHUL_CODE = SD_SCHUL_CODE;
////        this.SCHUL_NM = SCHUL_NM;
////        this.LCTN_SC_NM = LCTN_SC_NM;
//        this.code = code;
//        this.name = name;
//        this.location = location;
//    }

    public static SchoolDto toDto(School school) {
        return SchoolDto.builder()
                .schoolId(school.getSchoolId())
                .code(school.getCode())
                .name(school.getName())
                .location(school.getLocation())
                .build();
    }

}
