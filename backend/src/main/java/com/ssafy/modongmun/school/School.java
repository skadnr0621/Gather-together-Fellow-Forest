package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class School {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "SD_SCHUL_CODE")
    private Long code;

    @Column(name = "SCHUL_NM")
    private String name;

    @Column(name = "LCTN_SC_NM")
    private String location;

    public static School toEntity(SchoolDto schoolDto) {
        return School.builder()
                .code(schoolDto.getCode())
                .name(schoolDto.getName())
                .location(schoolDto.getLocation())
                .build();
    }

}
