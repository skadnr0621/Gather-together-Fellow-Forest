package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.board.comment.Comment;
import com.ssafy.modongmun.school.board.dto.PostDto;
import com.ssafy.modongmun.school.schedule.Schedule;
import com.ssafy.modongmun.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> boardList;

    public void update(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.createDate = LocalDateTime.now();
    }

}
