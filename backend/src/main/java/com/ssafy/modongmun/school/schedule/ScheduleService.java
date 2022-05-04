package com.ssafy.modongmun.school.schedule;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.schedule.dto.ScheduleRegisterDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;


    public void registerSchedule(ScheduleRegisterDto scheduleRegisterDto) {
        School school = schoolRepository.findById(scheduleRegisterDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(scheduleRegisterDto.getUserId()).orElse(null);

        Schedule schedule = Schedule.builder()
                .school(school)
                .user(user)
                .title(scheduleRegisterDto.getTitle())
                .location(scheduleRegisterDto.getLocation())
                .content(scheduleRegisterDto.getContent())
                .startDate(scheduleRegisterDto.getStartDate())
                .endDate(scheduleRegisterDto.getEndDate())
                .build();
        scheduleRepository.save(schedule);
    }

}
