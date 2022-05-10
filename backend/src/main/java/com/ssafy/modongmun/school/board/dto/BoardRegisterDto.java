package com.ssafy.modongmun.school.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardRegisterDto {
    //for comment
    private Long postId;

    private Long schoolId;
    private Long userId;

    private String title;
    private String content;
}
