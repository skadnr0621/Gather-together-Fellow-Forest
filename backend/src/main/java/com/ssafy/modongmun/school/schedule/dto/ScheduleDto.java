package com.ssafy.modongmun.school.schedule.dto;

import com.ssafy.modongmun.school.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ScheduleDto {

    private Long scheduleId;
    private Long schoolId;
    private Long userId;

    private String title;
    private String content;
    private String location;

    private LocalDate startDate;
    private LocalDate endDate;

    public static ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .scheduleId(schedule.getScheduleId())
                .schoolId(schedule.getSchool().getSchoolId())
                .userId(schedule.getUser().getUserId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .location(schedule.getLocation())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }

}
