package com.ssafy.modongmun.user.dto;

import com.ssafy.modongmun.school.dto.SchoolDto;
import com.ssafy.modongmun.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class SignupDto {

    private Long userNumber;
    private String username;

    private Long elementarySchoolId;
    private int egYear;

    private Long middleSchoolId;
    private int mgYear;

    private Long highSchoolId;
    private int hgYear;

    private Role role;

}
