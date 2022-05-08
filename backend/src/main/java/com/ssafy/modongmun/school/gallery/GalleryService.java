package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    public void postPhoto(GalleryPostDto galleryPostDto) {
        School school = schoolRepository.findById(galleryPostDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(galleryPostDto.getUserId()).orElse(null);

        Gallery gallery = Gallery.builder()
                .school(school)
                .user(user)
                .imgPath(galleryPostDto.getImgPath())
                .description(galleryPostDto.getDescription())
                .createDate(LocalDateTime.now())
                .build();

        galleryRepository.save(gallery);
    }

    public GalleryPostDto getPhoto(Long photoId) {

        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        GalleryPostDto galleryPostDto = new GalleryPostDto();
        galleryPostDto.setSchoolId(gallery.getSchool().getSchoolId());
        galleryPostDto.setUserId(gallery.getUser().getUserId());
        galleryPostDto.setImgPath(gallery.getImgPath());
        galleryPostDto.setDescription(gallery.getDescription());
        return galleryPostDto;
    }

    public List<GalleryPostDto> getPhotoList() {
        List<Gallery> galleryList = galleryRepository.findAll();
        System.out.println(galleryList);
        List<GalleryPostDto> galleryPostDtoList = new ArrayList<>();

        for(Gallery gallery : galleryList){
            GalleryPostDto galleryPostDto = new GalleryPostDto();
            galleryPostDto.setSchoolId(gallery.getSchool().getSchoolId());
            galleryPostDto.setUserId(gallery.getUser().getUserId());
            galleryPostDto.setImgPath(gallery.getImgPath());
            galleryPostDto.setDescription(gallery.getDescription());
            galleryPostDtoList.add(galleryPostDto);
        }

        return galleryPostDtoList;
    }

    public void deletePhoto(Long photoId) {
        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        galleryRepository.delete(gallery);
    }


}
