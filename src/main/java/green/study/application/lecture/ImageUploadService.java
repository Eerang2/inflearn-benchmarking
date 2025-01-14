package green.study.application.lecture;

import green.study.domain.lecture.model.LectureImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class ImageUploadService {

    @Value("${file.upload-dir}")
    String UPLOAD_DIR;


    @Transactional
    public LectureImage uploadAccommodationImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IOException("파일이 비어있습니다.");
        }

        // 파일 이름에 UUID 추가
        final String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        // 업로드 경로 절대 경로로 설정
        Path uploadPath = Paths.get(System.getProperty("user.dir"), UPLOAD_DIR, fileName);

        // 디렉토리가 존재하지 않으면 생성
        File directory = uploadPath.getParent().toFile();
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        // 파일 저장
        File destFile = uploadPath.toFile();
        imageFile.transferTo(destFile); // 실제 파일 저장

        // LectureImage 객체 반환
        return LectureImage.builder()
                .lectureImageName(imageFile.getOriginalFilename())
                .uniquePath(fileName)
                .path(uploadPath.toString()) // 절대 경로 반환
                .build();
    }
}
