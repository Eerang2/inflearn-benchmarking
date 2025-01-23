package green.study.domain.lecture.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum SubTags {

    // DEVELOPMENT 관련
    JAVA("자바", MainTags.DEVELOPMENT),
    SPRING("스프링", MainTags.DEVELOPMENT),
    HTML("HTML", MainTags.DEVELOPMENT),
    JS("JS", MainTags.DEVELOPMENT),
    SERVLET("Servlet", MainTags.DEVELOPMENT),

    // DESIGN 관련
    UI_UX("UI/UX", MainTags.DESIGN),
    GRAPHIC("그래픽 디자인", MainTags.DESIGN),
    MODELING("3D 모델링", MainTags.DESIGN),
    ILLUSTRATION("일러스트", MainTags.DESIGN),
    WEB_DESIGN("웹 디자인", MainTags.DESIGN),

    // MARKETING 관련
    SEO("SEO", MainTags.MARKETING),
    BRANDING("브랜드 전략", MainTags.MARKETING),
    DIGITAL_MARKETING("디지털 마케팅", MainTags.MARKETING),
    CAMPAIGN("광고 캠페인", MainTags.MARKETING),
    CONTENT("콘텐츠 제작", MainTags.MARKETING);

    private final String displayName;
    private final MainTags mainTags;

    // 대분류에 속한 소분류 리스트 가져오기
    public static List<SubTags> getSubTagsByMainCategory(MainTags mainTag) {
        return Arrays.stream(SubTags.values())
                .filter(subTag -> subTag.getMainTags().equals(mainTag))
                .collect(Collectors.toList());
    }

}
