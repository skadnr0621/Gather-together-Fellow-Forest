package com.ssafy.modongmun.school.gallery.dto;

import com.ssafy.modongmun.school.gallery.Gallery;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class GalleryPostDto {

    private Long photoId;
    private Long schoolId;
    private Long userId;
    private MultipartFile photo;
    private String description;

    public static GalleryPostDto toDto(Gallery gallery) {
        return GalleryPostDto.builder()
                .photoId(gallery.getPhotoId())
                .schoolId(gallery.getSchool().getSchoolId())
                .userId(gallery.getUser().getUserId())
                .description(gallery.getDescription())
                .build();
    }

}
