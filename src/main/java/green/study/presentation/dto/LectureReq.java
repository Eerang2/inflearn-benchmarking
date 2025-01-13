package green.study.presentation.dto;

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

        private String title;

        private MultipartFile banner;

        private String mainTag;

        private List<String> subTags;
    }
}
