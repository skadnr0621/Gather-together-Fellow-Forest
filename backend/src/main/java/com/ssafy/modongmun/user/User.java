package com.ssafy.modongmun.user;

import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.comment.Comment;
import com.ssafy.modongmun.school.gallery.Gallery;
import com.ssafy.modongmun.school.schedule.Schedule;
import com.ssafy.modongmun.user.dto.SignupDto;
import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_number")
    private Long userNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elementary_school_id")
    private School elementarySchool;
    @Column(name = "eg_year")
    private int egYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_school_id")
    private School middleSchool;
    @Column(name = "mg_year")
    private int mgYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "high_school_id")
    private School highSchool;
    @Column(name = "hg_year")
    private int hgYear;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    // Spring Security Role
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleKey() { return this.role.getKey(); }

    // OAuth2 provider identifier(distinguisher)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthProvider provider;

    // Mapped Entities
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Board> postList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Gallery> photoList;


//    public static User toEntity(SignupDto signupDto) {
//        return User.builder()
//                .userNumber(signupDto.getUserNumber())
//                .username(signupDto.getUsername())
//                .elementarySchool(School.toEntity(signupDto.getElementarySchoolDto()))
//                .egYear(signupDto.getEgYear())
//                .middleSchool(School.toEntity(signupDto.getMiddleSchoolDto()))
//                .mgYear(signupDto.getMgYear())
//                .highSchool(School.toEntity(signupDto.getHighSchoolDto()))
//                .hgYear(signupDto.getHgYear())
//                .registerDate(LocalDateTime.now())
//                .build();
//    }

    public void update(UserDto userDto) {
        this.elementarySchool = userDto.getElementarySchool();
        this.egYear = userDto.getEgYear();
        this.middleSchool = userDto.getMiddleSchool();
        this.mgYear = userDto.getMgYear();
        this.highSchool = userDto.getHighSchool();
        this.hgYear = userDto.getHgYear();
        this.birthYear = userDto.getBirthYear();
    }

}
