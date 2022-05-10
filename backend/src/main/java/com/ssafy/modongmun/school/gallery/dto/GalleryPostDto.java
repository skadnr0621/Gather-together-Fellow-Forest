package com.ssafy.modongmun.school.gallery.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class GalleryPostDto {

    private Long schoolId;
    private Long userId;
    private MultipartFile photo;
    private String description;

    // private byte[] detailPhoto;
    // base64로 전송할 때 필요
}
