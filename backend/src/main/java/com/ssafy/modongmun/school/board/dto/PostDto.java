package com.ssafy.modongmun.school.board.dto;

import com.ssafy.modongmun.school.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PostDto {

    private Long postId;

    private String username;

    private String title;
    private String content;
    private LocalDateTime createDate;


//    public static PostDto toDto(Post post) {
    public static PostDto toDto(Board board) {
            return PostDto.builder()
                    .postId(board.getPostId())
                    .username(board.getUser().getUsername())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createDate(board.getCreateDate())
                    .build();
    }

}
