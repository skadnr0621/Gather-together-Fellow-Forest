package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/gallery/photos")
    public ResponseEntity<?> postPhoto(GalleryPostDto galleryPostDto) throws IOException {
        MultipartFile photo = galleryPostDto.getPhoto();
        if(photo != null && !photo.isEmpty()){
            System.out.println("file 확인");
            String originName, saveFolder, imgPath;

            saveFolder = "C:" + File.separator + "PJT" + File.separator + "test0506" + File.separator;
            System.out.println("저장 경로 확인" + saveFolder);

            File folder = new File(saveFolder);
            if(!folder.exists()){
                System.out.println("폴더 생성");
                folder.mkdir();
            }

            originName = photo.getOriginalFilename();
            if(originName != null) {
                imgPath = saveFolder + originName;
                photo.transferTo(new File(saveFolder, originName));
                galleryPostDto.setImgPath(imgPath);
            }
        }
        galleryService.postPhoto(galleryPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<GalleryPostDto> getPhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        GalleryPostDto galleryPostDto = galleryService.getPhoto(photoId);

        // 이미지 반환
//        System.out.println(galleryPostDto.getImgPath());
//        InputStream photoStream = new FileInputStream(galleryPostDto.getImgPath());
//        galleryPostDto.setDetailPhoto(IOUtils.toByteArray(photoStream));

        return new ResponseEntity<>(galleryPostDto, HttpStatus.OK);
    }

    @GetMapping("/gallery/photos")
    public ResponseEntity<List<GalleryPostDto>> getPhotoList() throws Exception{
        List<GalleryPostDto> galleryPostDtoList = galleryService.getPhotoList();
//        InputStream photoStream;
//
//        for(GalleryPostDto galleryPostDto : galleryPostDtoList){
//            photoStream = new FileInputStream(galleryPostDto.getImgPath());
//            galleryPostDto.setDetailPhoto(IOUtils.toByteArray(photoStream));
//        }

        return new ResponseEntity<>(galleryPostDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/gallery/photos/{photo_id}")
    public ResponseEntity<?> deletePhoto(@PathVariable("photo_id") Long photoId) throws Exception{
        galleryService.deletePhoto(photoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
