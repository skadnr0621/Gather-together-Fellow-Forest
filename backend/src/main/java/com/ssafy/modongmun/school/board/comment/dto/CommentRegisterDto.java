package com.ssafy.modongmun.school.board.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentRegisterDto {
    private Long postId;
    private Long userId;
    private String content;
}
