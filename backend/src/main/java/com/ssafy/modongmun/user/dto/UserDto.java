package com.ssafy.modongmun.user.dto;

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

    private SchoolDto elementarySchool;
    private int egYear;

    private SchoolDto middleSchool;
    private int mgYear;

    private SchoolDto highSchool;
    private int hgYear;


    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .elementarySchool(SchoolDto.toDto(user.getElementarySchool()))
                .egYear(user.getEgYear())
                .middleSchool(SchoolDto.toDto(user.getMiddleSchool()))
                .mgYear(user.getMgYear())
                .highSchool(SchoolDto.toDto(user.getHighSchool()))
                .hgYear(user.getHgYear())
                .build();
    }

}
