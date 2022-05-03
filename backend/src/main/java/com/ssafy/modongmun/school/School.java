package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class School {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "SD_SCHUL_CODE")
    private Long SD_SCHUL_CODE;

    @Column(name = "SCHUL_NM")
    private String SCHUL_NM;

    @Column(name = "LCTN_SC_NM")
    private String LCTN_SC_NM;

    public static School toEntity(SchoolDto schoolDto) {
        return School.builder()
                .SD_SCHUL_CODE(schoolDto.getCode())
                .SCHUL_NM(schoolDto.getName())
                .LCTN_SC_NM(schoolDto.getLocation())
                .build();
    }

}
