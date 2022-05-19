package com.ssafy.modongmun.school.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findBySchool_schoolId(Long schoolId);
}
