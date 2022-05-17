package com.ssafy.modongmun.user.dto;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.dto.SchoolDto;
import com.ssafy.modongmun.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private Long userId;

    private String username;

    private School elementarySchool;
    private Long elementarySchoolId;
    private int egYear;

    private School middleSchool;
    private Long middleSchoolId;
    private int mgYear;

    private School highSchool;
    private Long highSchoolId;
    private int hgYear;

    private int birthYear;


    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .elementarySchoolId(user.getElementarySchool().getSchoolId())
                .egYear(user.getEgYear())
                .middleSchoolId(user.getMiddleSchool().getSchoolId())
                .mgYear(user.getMgYear())
                .highSchoolId(user.getHighSchool().getSchoolId())
                .hgYear(user.getHgYear())
                .birthYear(user.getBirthYear())
                .build();
    }

}
