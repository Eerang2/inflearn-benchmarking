package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.DescriptionEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Description {
    private Long key;
    private String content;
    private Long lectureKey;

    public DescriptionEntity toEntity() {
        return DescriptionEntity.builder()
                .content(content)
                .lectureKey(lectureKey)
                .build();
    }
}
