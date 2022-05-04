package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GalleryController {

    private final GalleryService galleryService;


//    @PostMapping("/gallery/photos")
//    public ResponseEntity<?> postPhoto(@RequestBody GalleryPostDto galleryPostDto) {
//
//    }
}
