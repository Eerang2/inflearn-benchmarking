package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Description {
    private Long key;
    private String content;
    private Long lectureKey;
}
