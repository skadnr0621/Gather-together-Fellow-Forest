package com.ssafy.modongmun.school.gallery.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class GalleryDto {

    private Long schoolId;
    private Long userId;
    private String imgPath;
    private String description;

}
