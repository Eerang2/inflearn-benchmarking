package green.study.presentation.api;

import green.study.application.lecture.service.LectureService;
import green.study.presentation.dto.LectureRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
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
    public List<LectureRes> freeCourses(Model model) {
        List<LectureRes> freeLectures = lectureService.getFreeLectures();
        System.out.println(freeLectures.get(0).getImagePath());
        System.out.println(freeLectures.get(0).getImageUniqueName());
        return freeLectures;

    }
}
