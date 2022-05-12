package com.ssafy.modongmun.school.board.comment.dto;

import com.ssafy.modongmun.school.board.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDto {

    private Long postId;
    private Long commentId;
    private Long userId;
    private String content;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .postId(comment.getBoard().getPostId())
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getUserId())
                .content(comment.getContent())
                .build();
    }

}

