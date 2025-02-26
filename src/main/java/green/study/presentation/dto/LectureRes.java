package green.study.presentation.dto;

import green.study.domain.lecture.entity.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureRes {

    private Long key;
    private String title;
    private int price;
    private String imagePath;
    private String imageUniqueName;
    private String description;
    private Long memberKey;

    public static LectureRes from(LectureEntity entity) {
        return LectureRes.builder()
                .key(entity.getKey())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .imageUniqueName(entity.getUniqueImageName())
                .imagePath(entity.getImagePath())
                .description(entity.getDescription())
                .memberKey(entity.getKey())
                .build();

    }
}
