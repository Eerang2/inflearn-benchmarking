package green.study.application.lecture;


import green.study.application.common.BaseTest;
import green.study.application.lecture.service.LectureService;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.enums.SubTags;
import green.study.domain.lecture.model.Lecture;
import green.study.domain.lecture.model.LectureImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class LectureServiceTest extends BaseTest {

    @Autowired
    private LectureService lectureService;

    @Test
    @DisplayName("배너 등록 성공")
    @Transactional
    void successBanner() {
        Lecture lecture = Lecture.builder()
                .title("제목1")
                .description("설명1")
                .memberKey(1L)
                .image(LectureImage.builder()
                        .lectureImageName("image1")
                        .uniquePath("jsdisidna_image1.jpg")
                        .path("/static/images/banner")
                        .build())
                .price(1999999)
                .build();

        MainTags mainTags = MainTags.DEVELOPMENT;

        List<SubTags> subTags = new ArrayList<>();
        subTags.add(SubTags.JS);
        subTags.add(SubTags.JAVA);
        subTags.add(SubTags.SPRING);

//        lectureService.createBanner(lecture, mainTags, subTags);
    }
}
