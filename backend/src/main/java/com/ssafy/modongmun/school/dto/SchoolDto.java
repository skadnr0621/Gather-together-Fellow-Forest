package com.ssafy.modongmun.school.dto;

import com.ssafy.modongmun.school.School;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class SchoolDto {

    private Long schoolId;

//    private Long SD_SCHUL_CODE;
//    private String SCHUL_NM;
//    private String LCTN_SC_NM;

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
//                .SD_SCHUL_CODE(school.getSD_SCHUL_CODE())
//                .SCHUL_NM(school.getSCHUL_NM())
//                .LCTN_SC_NM(school.getLCTN_SC_NM())
                .code(school.getSD_SCHUL_CODE())
                .name(school.getSCHUL_NM())
                .location(school.getLCTN_SC_NM())
                .build();
    }

}
