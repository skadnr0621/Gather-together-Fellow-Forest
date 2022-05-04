package com.ssafy.modongmun.school.schedule;

import com.ssafy.modongmun.school.schedule.dto.ScheduleRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping("/schedule/schedules")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleRegisterDto scheduleRegisterDto) {
        scheduleService.registerSchedule(scheduleRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
