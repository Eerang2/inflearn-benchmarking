package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureImage {

    private Long lectureImageKey;
    private String lectureImageName;
    private String path;
    private String uniquePath;
    private Long lectureKey;
}
