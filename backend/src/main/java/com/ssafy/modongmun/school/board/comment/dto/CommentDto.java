package com.ssafy.modongmun.school.board.comment.dto;

import com.ssafy.modongmun.school.board.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDto {

    private Long postId;
    private Long commentId;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createDate;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getBoard().getPostId())
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getUserId())
                .username(comment.getUser().getUsername())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .build();
    }

}