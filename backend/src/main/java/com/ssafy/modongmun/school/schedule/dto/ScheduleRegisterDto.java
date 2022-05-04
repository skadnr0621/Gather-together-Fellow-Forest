package com.ssafy.modongmun.school.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ScheduleRegisterDto {

    private Long schoolId;

    private Long userId;

    private String title;
    private String location;
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;

}
