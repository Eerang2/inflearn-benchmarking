package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.LectureEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lecture {

    private Long key;
    private String title;
    private Double price;
    private String description;
    private LectureImage image;
    private Long memberKey;

    public static Lecture from(LectureEntity entity) {
        return Lecture.builder()
                .key(entity.getKey())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .image(LectureImage.builder()
                        .lectureImageName(entity.getImageName())
                        .uniquePath(entity.getImageUniquePath())
                        .build())
                .memberKey(entity.getMemberKey())
                .build();
    }

    public LectureEntity toEntity() {
        return LectureEntity.builder()
                .title(title)
                .description(description)
                .price(price)
                .imageName(image.getLectureImageName())
                .imageUniquePath(image.getUniquePath())
                .memberKey(memberKey)
                .build();

    }
}
