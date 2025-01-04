package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureImage {

    private String url;
    private String path;
    private Long lectureId;
}
