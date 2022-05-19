package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.gallery.dto.GalleryDto;
import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    public GalleryPostDto postPhoto(GalleryPostDto galleryPostDto) {
        // MultiparFile을 저장하고 저장 경로를 받아옵니다.
        String url = null;
        try {
            url = awsS3Service.uploadFile(galleryPostDto.getPhoto());
            log.info("Successfully saved resource :: " + url);
        }
        catch (IOException e) {
            log.info("url was not found");
            e.printStackTrace();
        }

        // Gallery entity 저장
        School school = schoolRepository.findById(galleryPostDto.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal school ID !"));
        User user = userRepository.findById(galleryPostDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal user ID !"));

        Gallery savedGallery = galleryRepository.save(
                Gallery.builder()
                        .school(school)
                        .user(user)
                        .imgPath(url)
                        .description(galleryPostDto.getDescription())
                        .createDate(LocalDateTime.now())
                        .build()
        );

        return GalleryPostDto.toDto(savedGallery);
    }

    public GalleryDto getPhoto(Long photoId) throws  IOException {

        Gallery gallery = galleryRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Illegal gallery id !"));

        return GalleryDto.toDto(gallery);
    }

    public List<GalleryDto> getPhotoList(Long schoolId) throws IOException {

        List<Gallery> galleryList = galleryRepository.findBySchool_schoolId(schoolId);
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for(Gallery gallery : galleryList){
            GalleryDto galleryDto = GalleryDto.toDto(gallery);
            galleryDtoList.add(galleryDto);
        }

        return galleryDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public GalleryDto deletePhoto(Long photoId) {
        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        galleryRepository.delete(gallery);

        return GalleryDto.toDto(gallery);
    }

}