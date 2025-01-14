package green.study.presentation.dto;

import green.study.domain.lecture.model.Lecture;
import green.study.domain.lecture.model.LectureImage;
import lombok.*;

import java.util.List;

@Getter
public class LectureReq {

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Banner {

        private String title;

        private String description;

        private String imageName;

        private String imageUniquePath;

        private String mainCategory;

        private List<String> subTags;

        public Lecture toLecture(Long memberKey) {
            return Lecture.builder()
                    .title(title)
                    .description(description)
                    .image(LectureImage.builder()
                            .lectureImageName(imageName)
                            .uniquePath(imageUniquePath)
                            .build())
                    .memberKey(memberKey)
                    .build();
        }
    }
}
