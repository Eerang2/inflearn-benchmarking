package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureVideo {
    private Long key;
    private String chapter;
    private Long lectureKey;
    private Long videoKey;
}
