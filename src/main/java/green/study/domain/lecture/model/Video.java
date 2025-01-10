package green.study.domain.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Video {

    private Long key;
    private String title;
    private String uniquePath;
    private String time;
    private String complete;
}
