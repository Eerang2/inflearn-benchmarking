package green.study.presentation.api;

import green.study.application.lecture.LectureService;
import green.study.domain.lecture.enums.MainTags;
import green.study.presentation.dto.LectureReq;
import green.study.presentation.dto.LectureSubTagsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureRestController {

    private final LectureService lectureService;

    @GetMapping("/subcategories/{mainCategory}")
    public List<LectureSubTagsRes> getSubCategories(@PathVariable MainTags mainCategory) {
        return lectureService.getSubCategories(mainCategory);
    }

    @PostMapping("/create/banner")
    public ResponseEntity<String> createBanner(@RequestBody LectureReq.Banner banner) {
        //  : 이미지 이름 암호화하고, static/images/banner 에 경로 저장
        System.out.println(banner.getSubTags().get(1));
        //  : 배너 관련 저장로직 구현
        System.out.println(banner.getBanner());

        //  : response.ok return
        return null;
    }
}
