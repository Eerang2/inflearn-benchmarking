package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureTags {

    private String lectureTagKey;
    private String tagName;
    private String parentKey;
}
