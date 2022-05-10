package com.ssafy.modongmun.school.gallery;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.gallery.dto.GalleryPostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    public void postPhoto(GalleryPostDto galleryPostDto) throws IOException {

        MultipartFile photo = galleryPostDto.getPhoto();
        if(photo != null && !photo.isEmpty()){
            System.out.println("file 확인");
            String originName, saveFolder, imgPath;

            saveFolder = "C:" + File.separator + "PJT" + File.separator + "testS3" + File.separator;
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

    public GalleryPostDto getPhoto(Long photoId) throws IOException {

        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        GalleryPostDto galleryPostDto = new GalleryPostDto();
        galleryPostDto.setSchoolId(gallery.getSchool().getSchoolId());
        galleryPostDto.setUserId(gallery.getUser().getUserId());
        galleryPostDto.setImgPath(gallery.getImgPath());
        galleryPostDto.setDescription(gallery.getDescription());
        // InputStream photoStream = new FileInputStream(gallery.getImgPath());
        // galleryPostDto.setDetailPhoto(IOUtils.toByteArray(photoStream));

        return galleryPostDto;
    }

    public List<GalleryPostDto> getPhotoList() throws IOException {

        List<Gallery> galleryList = galleryRepository.findAll();
        List<GalleryPostDto> galleryPostDtoList = new ArrayList<>();
        // InputStream photoStream;


        for(Gallery gallery : galleryList){
            GalleryPostDto galleryPostDto = new GalleryPostDto();
            galleryPostDto.setSchoolId(gallery.getSchool().getSchoolId());
            galleryPostDto.setUserId(gallery.getUser().getUserId());
            galleryPostDto.setImgPath(gallery.getImgPath());
            galleryPostDto.setDescription(gallery.getDescription());
            // photoStream = new FileInputStream(galleryPostDto.getImgPath());
            // galleryPostDto.setDetailPhoto(IOUtils.toByteArray(photoStream));
            galleryPostDtoList.add(galleryPostDto);
        }

        return galleryPostDtoList;
    }

    public void deletePhoto(Long photoId) {
        Gallery gallery = galleryRepository.findById(photoId).orElse(null);
        galleryRepository.delete(gallery);
    }

}
