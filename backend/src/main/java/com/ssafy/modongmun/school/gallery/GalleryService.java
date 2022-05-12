package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.gallery.dto.GalleryDto;
import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryService {

    /*** Service ***/
    private final AwsS3Service awsS3Service;
    /*** Repository ***/
    private final GalleryRepository galleryRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void postPhoto(GalleryPostDto galleryPostDto) {
        // MultiparFile을 저장하고 저장 경로를 받아옵니다.
        String url = null;
        try {
            url = awsS3Service.uploadFile(galleryPostDto.getPhoto());
            if (url == null)
                return;
            log.info("Successfully saved resource :: " + url);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        // Gallery entity 저장
        School school = schoolRepository.findById(galleryPostDto.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal school ID !"));
        User user = userRepository.findById(galleryPostDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal user ID !"));

        galleryRepository.save(
                Gallery.builder()
                        .school(school)
                        .user(user)
                        .imgPath(url)
                        .description(galleryPostDto.getDescription())
                        .createDate(LocalDateTime.now())
                        .build()
        );
    }

    public GalleryDto getPhoto(Long photoId) throws  IOException {

        Gallery gallery = galleryRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Illegal gallery id !"));

        GalleryDto galleryDto = GalleryDto.builder()
                                        .schoolId(gallery.getSchool().getSchoolId())
                                        .userId(gallery.getUser().getUserId())
                                        .imgPath(gallery.getImgPath())
                                        .description(gallery.getDescription())
                                        .build();

        return galleryDto;
    }

    public List<GalleryDto> getPhotoList() throws IOException {

        List<Gallery> galleryList = galleryRepository.findAll();
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for(Gallery gallery : galleryList){
            GalleryDto galleryDto = GalleryDto.builder()
                    .schoolId(gallery.getSchool().getSchoolId())
                    .userId(gallery.getUser().getUserId())
                    .imgPath(gallery.getImgPath())
                    .description(gallery.getDescription())
                    .build();
            galleryDtoList.add(galleryDto);
        }

        return galleryDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePhoto(Long photoId) {
        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        galleryRepository.delete(gallery);
    }

}
