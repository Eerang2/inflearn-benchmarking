package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.TagEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureTags {
    private Long lectureTagKey;
    private String tagName;
    private Long parentKey;
    private Long lectureKey;

    public static TagEntity toMainTagEntity(String tagName, Long lectureKey) {
        return TagEntity.builder()
                .tagName(tagName)
                .parentsTagId(null)
                .lectureKey(lectureKey)
                .build();
    }

    public static TagEntity toSubTagEntity(String tagName, Long lectureKey, Long parentKey) {
        return TagEntity.builder()
                .tagName(tagName)
                .parentsTagId(parentKey)
                .lectureKey(lectureKey)
                .build();
    }
}
