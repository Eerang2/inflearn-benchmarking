package green.study.domain.lecture.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainTags {
    DEVELOPMENT("개발"), DESIGN("디자인"), MARKETING("마케팅"), SECURITY("보안"), ;
    private final String displayName;
}
