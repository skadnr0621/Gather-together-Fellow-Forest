package com.ssafy.modongmun.user;

import com.ssafy.modongmun.user.dto.SignupDto;
import com.ssafy.modongmun.school.School;
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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_number")
    private Long userNumber;

    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "elementary_school_id")
    private School elementarySchool;
    @Column(name = "eg_year")
    private int egYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "middle_school_id")
    private School middleSchool;
    @Column(name = "mg_year")
    private int mgYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "high_school_id")
    private School highSchool;
    @Column(name = "hg_year")
    private int hgYear;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    public static User toEntity(SignupDto signupDto) {
        return User.builder()
                .userNumber(signupDto.getUserNumber())
                .username(signupDto.getUsername())
                .elementarySchool(School.toEntity(signupDto.getElementarySchoolDto()))
                .egYear(signupDto.getEgYear())
                .middleSchool(School.toEntity(signupDto.getMiddleSchoolDto()))
                .mgYear(signupDto.getMgYear())
                .highSchool(School.toEntity(signupDto.getHighSchoolDto()))
                .hgYear(signupDto.getHgYear())
                .registerDate(LocalDateTime.now())
                .build();

    }

}
