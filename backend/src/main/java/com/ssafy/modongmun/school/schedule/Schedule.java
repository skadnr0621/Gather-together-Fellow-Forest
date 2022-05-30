package com.ssafy.modongmun.school.schedule;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.schedule.dto.ScheduleDto;
import com.ssafy.modongmun.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;
    @Column(name = "location")
    private String location;
    @Column(name= "content")
    private String content;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public void update(ScheduleDto scheduleDto) {
        this.title = scheduleDto.getTitle();
        this.content = scheduleDto.getContent();
        this.location = scheduleDto.getLocation();
        this.startDate = scheduleDto.getStartDate();
        this.endDate = scheduleDto.getEndDate();
        this.createDate = LocalDateTime.now();
    }

}
