package green.study.presentation.api;

import green.study.application.lecture.ImageUploadService;
import green.study.application.lecture.LectureService;
import green.study.domain.exceptions.ExpiredTokenException;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.model.LectureImage;
import green.study.domain.member.model.Member;
import green.study.domain.model.GetToken;
import green.study.domain.model.Token;
import green.study.infrastructure.util.JwtUtil;
import green.study.presentation.dto.LectureReq;
import green.study.presentation.dto.LectureSubTagsRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureRestController {

    private final LectureService lectureService;
    private final ImageUploadService imageUploadService;
    private final JwtUtil jwtUtil;

    @GetMapping("/subcategories/{mainCategory}")
    public List<LectureSubTagsRes> getSubCategories(@PathVariable MainTags mainCategory) {
        return lectureService.getSubCategories(mainCategory);
    }

    @PostMapping("/create/banner")
    public ResponseEntity<String> createBanner(@GetToken Token token,
                                               @RequestBody @Valid LectureReq.Banner bannerReq) {

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
        return imageUploadService.uploadAccommodationImage(thumbnail);
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
}
