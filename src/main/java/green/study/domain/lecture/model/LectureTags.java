package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureTags {
    private Long lectureTagKey;
    private String tagName;
    private Long parentKey;
    private Long lectureKey;
}
