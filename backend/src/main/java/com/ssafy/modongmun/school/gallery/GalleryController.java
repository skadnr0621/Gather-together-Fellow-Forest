package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/gallery/photos")
    public ResponseEntity<?> postPhoto(@RequestBody GalleryPostDto galleryPostDto) throws IOException {
        galleryService.postPhoto(galleryPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<GalleryPostDto> getPhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        GalleryPostDto galleryPostDto = galleryService.getPhoto(photoId);
        return new ResponseEntity<>(galleryPostDto, HttpStatus.OK);
    }

    @GetMapping("/gallery/photos")
    public ResponseEntity<List<GalleryPostDto>> getPhotoList() throws Exception{
        List<GalleryPostDto> galleryPostDtoList = galleryService.getPhotoList();
        return new ResponseEntity<>(galleryPostDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<?> deletePhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        galleryService.deletePhoto(photoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
