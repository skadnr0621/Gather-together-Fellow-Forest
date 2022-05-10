package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
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


    //post로 써있지만 서버는 board로 인식하게 하는 annotation을 찾아서 붙여줘야 한다.
    // ERD >> 엔티티 클래스를 만들면 그 엔티티 이름으로 테이블 -? 말고 설정을 줄 수 있는 테이블 어노테이션
    // -> 이해못했음.

}
