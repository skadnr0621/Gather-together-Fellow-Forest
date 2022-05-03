package com.ssafy.modongmun.user.dto;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupDto {

    private Long userNumber;
    private String username;

    private SchoolDto elementarySchoolDto;
    private int egYear;

//    private String middleSchoolName;
//    private String middleSchoolLocation;
    private SchoolDto middleSchoolDto;
    private int mgYear;

//    private String highSchoolName;
//    private String highSchoolLocation;
    private SchoolDto highSchoolDto;
    private int hgYear;

}
