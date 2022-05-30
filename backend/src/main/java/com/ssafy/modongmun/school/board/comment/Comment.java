package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.comment.dto.CommentDto;
import com.ssafy.modongmun.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="content")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public void update(CommentDto commentDto){
        this.content = commentDto.getContent();
        this.createDate = LocalDateTime.now();
    }

}
