package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Lecture {

    private Long key;
    private String title;
    private int price;
    private String description;
    private LectureImage image;
    private int likeCount;
    private LocalDateTime createTime;
    private Long memberKey;

    public static Lecture from(LectureEntity entity) {
        return Lecture.builder()
                .key(entity.getKey())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .image(LectureImage.builder()
                        .lectureImageName(entity.getUniqueImageName())
                        .path(entity.getImagePath())
                        .build())
                .memberKey(entity.getMemberKey())
                .createTime(entity.getCreateTime())
                .likeCount(entity.getLikeCount())
                .build();
    }

    public LectureEntity toEntity() {
        return LectureEntity.builder()
                .title(title)
                .description(description)
                .price(price)
                .uniqueImageName(image.getLectureImageName())
                .imagePath(image.getPath())
                .memberKey(memberKey)
                .build();

    }

    public Lecture(Long key, String title, String description, int price, int likeCount, Long memberKey) {
        this.key = key;
        this.title = title;
        this.price = price;
        this.description = description;
        this.likeCount = likeCount;
        this.memberKey = memberKey;
    }
}
