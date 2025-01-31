package green.study.application.lecture.service;

import green.study.application.lecture.service.dto.FileRes;
import green.study.domain.lecture.model.LectureImage;
import green.study.domain.lecture.model.Video;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FileUploadService {

    @Value("${file.image.upload-dir}")
    String IMAGE_UPLOAD_DIR;

    @Value("${file.video.upload-dir}")
    String VIDEO_UPLOAD_DIR;

    @Transactional
    public LectureImage uploadBanner(MultipartFile imageFile) throws IOException {

        FileRes fileRes = uploadFile(imageFile, IMAGE_UPLOAD_DIR);
        // LectureImage 객체 반환
        return LectureImage.builder()
                .lectureImageName(imageFile.getOriginalFilename())
                .uniqueName(fileRes.getFileName())
                .path(IMAGE_UPLOAD_DIR) // 절대 경로 반환
                .build();
    }

    @Transactional
    public Video saveVideoToDisk(MultipartFile videoFile, String videoName) throws IOException {
        FileRes fileRes = uploadFile(videoFile, VIDEO_UPLOAD_DIR);
        return Video.builder()
                .videoName(videoFile.getOriginalFilename())
                .title(videoName)
                .uniquePath(VIDEO_UPLOAD_DIR)
                .build();

    }


    private FileRes uploadFile(MultipartFile file, final String uploadDir) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("파일이 비어있습니다.");
        }
        final String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        // 업로드 경로 절대 경로로 설정
        Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadDir, fileName);

        // 디렉토리가 존재하지 않으면 생성
        File directory = uploadPath.getParent().toFile();
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        // 파일 저장
        File destFile = uploadPath.toFile();
        file.transferTo(destFile); // 실제 파일 저장

        return FileRes.builder()
                .fileName(fileName)
                .filePath(uploadPath)
                .build();
    }
}
