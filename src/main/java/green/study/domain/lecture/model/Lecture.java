package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lecture {

    private Long key;
    private String title;
    private String description;
    private LectureImage image;
    private Long memberKey;
}
