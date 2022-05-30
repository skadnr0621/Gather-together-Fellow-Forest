package com.ssafy.modongmun.school;

import com.ssafy.modongmun.school.dto.SchoolDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findByCode(Long code);
    List<School> findByNameContaining(@Param("keyword") String keyword);
}
