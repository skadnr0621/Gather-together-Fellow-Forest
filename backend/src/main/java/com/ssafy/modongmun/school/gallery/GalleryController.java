package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.gallery.dto.GalleryDto;
import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/gallery/photos")
    public ResponseEntity<GalleryPostDto> postPhoto(@ModelAttribute GalleryPostDto galleryPostDto) throws IOException {
        GalleryPostDto savedGallery = galleryService.postPhoto(galleryPostDto);
        return new ResponseEntity<GalleryPostDto>(savedGallery, HttpStatus.OK);
    }

    @GetMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<GalleryDto> getPhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        GalleryDto galleryDto = galleryService.getPhoto(photoId);
        return new ResponseEntity<GalleryDto>(galleryDto, HttpStatus.OK);
    }

    @GetMapping("/gallery/photos")
    public ResponseEntity<List<GalleryDto>> getPhotoList(@RequestParam("schoolId") Long schoolId) throws Exception{
        List<GalleryDto> galleryDtoList = galleryService.getPhotoList(schoolId);
        return new ResponseEntity<List<GalleryDto>>(galleryDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<GalleryDto> deletePhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        GalleryDto deletedGallery = galleryService.deletePhoto(photoId);
        return new ResponseEntity<GalleryDto>(deletedGallery, HttpStatus.OK);
    }
}
