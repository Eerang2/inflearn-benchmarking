package green.study.presentation.api;

import green.study.application.lecture.LectureService;
import green.study.domain.lecture.enums.MainTags;
import green.study.presentation.dto.LectureSubTagsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
