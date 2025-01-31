package green.study.presentation.api;

import green.study.application.lecture.service.LectureService;
import green.study.presentation.dto.LectureRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainRestController {

    private final LectureService lectureService;

    @GetMapping("/recommend/free-backend-lectures")
    public List<LectureRes> freeLectures() {
        return lectureService.getFreeLectures();
    }

    @GetMapping("/recommend/new-lectures")
    public List<LectureRes> recentLectures() {
        return lectureService.getRecentLectures();
    }
}
