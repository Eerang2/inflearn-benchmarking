package green.study.presentation.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import green.study.application.lecture.FileUploadService;
import green.study.application.lecture.LectureService;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.model.Chapter;
import green.study.domain.lecture.model.LectureImage;
import green.study.domain.lecture.model.Video;
import green.study.domain.member.exceptions.InvalidMetadataException;
import green.study.domain.member.model.Member;
import green.study.domain.model.GetToken;
import green.study.domain.model.Token;
import green.study.infrastructure.util.JwtUtil;
import green.study.presentation.dto.LectureReq;
import green.study.presentation.dto.LectureSubTagsRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static green.study.domain.model.Token.validateToken;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class LectureRestController {

    private final LectureService lectureService;
    private final FileUploadService fileUploadService;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @GetMapping("/subcategories/{mainCategory}")
    public List<LectureSubTagsRes> getSubCategories(@PathVariable MainTags mainCategory) {
        return lectureService.getSubCategories(mainCategory);
    }

    @PostMapping("/create/banner")
    public ResponseEntity<String> createBanner(@RequestBody LectureReq.Banner bannerReq,
                                               @GetToken Token token) {

        validateToken(token);
        Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
        lectureService.createBanner(bannerReq.toLecture(member.getKey()), bannerReq.getMainCategory(), bannerReq.getSubTags());

        //  : response.ok return
        return ResponseEntity.ok("create banner");
    }

    @PostMapping("/create/thumbnail")
    public LectureImage createThumbnail(@RequestParam("banner") MultipartFile thumbnail) throws IOException {
        return fileUploadService.uploadBanner(thumbnail);
    }

    @PostMapping("/create/description")
    public ResponseEntity<String> createDescription(@RequestBody @Valid LectureReq.Description description,
                                                    @GetToken Token token) {

        validateToken(token);
        Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
        lectureService.saveDescription(description.toDescription(member.getKey()));
        return ResponseEntity.ok("create description");
    }

    @PostMapping("/save/lecture-video")
    public ResponseEntity<String> saveLectureVideoRelation(@RequestPart("metadata") String metadata,
                                                           @RequestPart("videoFiles") List<MultipartFile> videoFiles,
                                                           @GetToken Token token) throws IOException {

        validateToken(token);
        List<LectureReq.ChapterDto> chapters = parseMetadata(metadata);

        Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
        Long lectureKey = lectureService.findLectureByMemberKey(member.getKey());

        processChapters(chapters, videoFiles, lectureKey);

        return ResponseEntity.ok("Lecture saved successfully");
    }



    // Json 객체 Java 객체로 parsing 메서드
    private List<LectureReq.ChapterDto> parseMetadata(String metadata) {
        try {
            return objectMapper.readValue(metadata, new TypeReference<>() {});
        } catch (Exception e) {
            throw new InvalidMetadataException("Invalid metadata format", e);
        }
    }

    // 챕터와 챕터영상들 저장 로직 메서드
    private void processChapters(List<LectureReq.ChapterDto> chapters, List<MultipartFile> videoFiles, Long lectureKey) throws IOException {
        int fileIndex = 0;

        for (LectureReq.ChapterDto chapter : chapters) {
            Chapter savedChapter = lectureService.saveChapter(chapter.getChapterName(), lectureKey);

            for (LectureReq.VideoDto video : chapter.getVideos()) {
                MultipartFile videoFile = getVideoFile(videoFiles, fileIndex++);
                Video uploadedVideo = fileUploadService.saveVideoToDisk(videoFile, video.getVideoTitle());
                lectureService.saveVideo(uploadedVideo, savedChapter.getKey());
            }
        }
    }

    // Multipart data 존재여부 확인 후 배열값 반환
    private MultipartFile getVideoFile(List<MultipartFile> videoFiles, int index) {
        if (index >= videoFiles.size()) {
            throw new IllegalArgumentException("Video file count does not match metadata");
        }
        return videoFiles.get(index);
    }
}
