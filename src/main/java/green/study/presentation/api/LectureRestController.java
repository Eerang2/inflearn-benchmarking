package green.study.presentation.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import green.study.application.lecture.FileUploadService;
import green.study.application.lecture.LectureService;
import green.study.domain.exceptions.ExpiredTokenException;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.model.Chapter;
import green.study.domain.lecture.model.LectureImage;
import green.study.domain.lecture.model.Video;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class LectureRestController {

    private final LectureService lectureService;
    private final FileUploadService fileUploadService;
    private final JwtUtil jwtUtil;

    @GetMapping("/subcategories/{mainCategory}")
    public List<LectureSubTagsRes> getSubCategories(@PathVariable MainTags mainCategory) {
        return lectureService.getSubCategories(mainCategory);
    }

    @PostMapping("/create/banner")
    public ResponseEntity<String> createBanner(@RequestBody LectureReq.Banner bannerReq,
                                               @GetToken Token token) {

        if (token == null) {
            throw new ExpiredTokenException();
        }
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

        if (token == null) {
            throw new ExpiredTokenException();
        }
        Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
        lectureService.saveDescription(description.toDescription(member.getKey()));
        return ResponseEntity.ok("create description");
    }

    @PostMapping("/save/lecture-video")
    public ResponseEntity<String> saveLectureVideoRelation(@RequestPart("metadata") String metadata,
                                                           @RequestPart("videoFiles") List<MultipartFile> videoFiles,
                                                           @GetToken Token token) throws IOException {

        if (token == null) {
            throw new ExpiredTokenException();
        }
        // 1. JSON 데이터를 파싱 (metadata)
        ObjectMapper objectMapper = new ObjectMapper();
        List<LectureReq.ChapterDto> chapters;
        try {
            chapters = objectMapper.readValue(metadata, new TypeReference<>() {});
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid metadata format");
        }

        Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
        int fileIndex = 0; // 파일 인덱스

        Long lectureKey = lectureService.findLectureByMemberKey(member.getKey());
        for (LectureReq.ChapterDto chapter : chapters) {

            // DB 저장
            Chapter lectureVideo = lectureService.saveChapter(chapter.getChapterName(), lectureKey);
            for (LectureReq.VideoDto video : chapter.getVideos()) {

                // 파일 업로드
                MultipartFile videoFile = videoFiles.get(fileIndex); // 파일 매칭

                // DB 저장
                Video uploaded = fileUploadService.saveVideoToDisk(videoFile, video.getVideoTitle());
                lectureService.saveVideo(uploaded, lectureVideo.getKey());
                fileIndex++;
            }
        }

        return ResponseEntity.ok("Lecture saved successfully");
    }
}
