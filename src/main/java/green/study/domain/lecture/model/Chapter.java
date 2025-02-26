package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.ChapterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private Long key;
    private String chapter;
    private Long lectureKey;

    public static Chapter from(ChapterEntity lectureVideoEntity) {
        return Chapter.builder()
                .key(lectureVideoEntity.getKey())
                .chapter(lectureVideoEntity.getChapter())
                .lectureKey(lectureVideoEntity.getLectureKey())
                .build();
    }

    public ChapterEntity toEntity() {
        return ChapterEntity.builder()
                .key(key)
                .chapter(chapter)
                .lectureKey(lectureKey)
                .build();
    }

    public Chapter(String chapter, Long lectureKey) {
        this.chapter = chapter;
        this.lectureKey = lectureKey;
    }
}
