package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GalleryController {

    private final GalleryService galleryService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/gallery/photos/s3")
    public String postPhotoS3(@RequestPart(value = "s3photo")MultipartFile multipartFile) throws Exception {
        return awsS3Service.uploadFile(multipartFile);

    }

    @PostMapping("/gallery/photos")
    public ResponseEntity<?> postPhoto(@ModelAttribute GalleryPostDto galleryPostDto, @RequestPart(value = "s3photo")MultipartFile multipartFile) throws IOException {
        // 1. galleryPostDto.getPhoto
        // 2. s3photo 받고 setPhoto 해주기

        galleryPostDto.setPhoto(multipartFile);
        galleryService.postPhoto(galleryPostDto);
        awsS3Service.uploadFile(multipartFile);

        //String url = awsS3Service.uploadFile(multipartFile);
        //System.out.println(url);
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
