package com.ssafy.modongmun.school.schedule;

import com.ssafy.modongmun.school.schedule.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule/schedules")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleDto scheduleRegisterDto) {
        scheduleService.registerSchedule(scheduleRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/schedule/schedules/{schedule_id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable("schedule_id") Long scheduleId) {
        ScheduleDto scheduleDto = scheduleService.getSchedule(scheduleId);
        return new ResponseEntity<ScheduleDto>(scheduleDto, HttpStatus.OK);
    }

    @GetMapping("/schedule/schedules")
    public ResponseEntity<List<ScheduleDto>> getScheduleList() {
        List<ScheduleDto> scheduleDtoList = scheduleService.getScheduleList();
        return new ResponseEntity<List<ScheduleDto>>(scheduleDtoList, HttpStatus.OK);
    }

    @PatchMapping("/schedule/schedules/{schedule_id}")
    public ResponseEntity<?> modifySchedule(@PathVariable("schedule_id") Long scheduleId,
                                            @RequestBody ScheduleDto scheduleDto){
        scheduleService.modifySchedule(scheduleId, scheduleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/schedule/schedules/{schedule_id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("schedule_id") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
