package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureImage {

    private String lectureImageName;
    private String uniquePath;
    private String path;
}
