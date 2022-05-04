package com.ssafy.modongmun.school.gallery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class GalleryPostDto {

    private MultipartFile photo;

    private Long userId;

//    private
}
