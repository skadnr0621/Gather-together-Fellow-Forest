package com.ssafy.modongmun.school.board.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentRegisterDto {
    //게시글 id : 이건 뭐지그럼???
    private Long postId;
    //댓글 id
    private Long commentId;
    //user id
    private Long userId;
    //댓글 내용
    private String content;

    //댓글, 게시글 작성 시간 추가 예정
}
