package com.ssafy.modongmun.school.gallery.dto;

import com.ssafy.modongmun.school.gallery.Gallery;
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

    public static GalleryDto toDto(Gallery gallery) {
        return GalleryDto.builder()
                .schoolId(gallery.getSchool().getSchoolId())
                .userId(gallery.getUser().getUserId())
                .imgPath(gallery.getImgPath())
                .description(gallery.getDescription())
                .build();
    }

}
