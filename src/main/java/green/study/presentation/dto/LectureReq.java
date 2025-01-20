package green.study.presentation.dto;

import green.study.domain.lecture.model.Lecture;
import green.study.domain.lecture.model.LectureImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class LectureReq {

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Banner {

        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "대표설명은 필수입니다.")
        private String description;

        @NotBlank(message = "가격은 필수입니다.")
        private Double price;

        @NotBlank(message = "이미지가 비어있습니다.")
        private String imageName;

        @NotBlank(message = "이미지가 비어있습니다.")
        private String imageUniquePath;

        @NotNull
        private String mainCategory;

        @NotBlank(message = "소분류 태그는 필수입니다.")
        private List<String> subTags;

        public Lecture toLecture(Long memberKey) {
            return Lecture.builder()
                    .title(title)
                    .price(price)
                    .description(description)
                    .image(LectureImage.builder()
                            .lectureImageName(imageName)
                            .uniquePath(imageUniquePath)
                            .build())
                    .memberKey(memberKey)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Description {

        @NotBlank(message = "설명은 필수입니다.")
        private String description;

        public green.study.domain.lecture.model.Description toDescription(Long lectureKey) {
            return green.study.domain.lecture.model.Description.builder()
                    .content(description)
                    .lectureKey(lectureKey)
                    .build();
        }
    }



    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VideoDto {

        private String videoTitle;
        private MultipartFile videoFile;
    }

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChapterDto {
        private String chapterName;
        private List<VideoDto> videos;
    }

}
