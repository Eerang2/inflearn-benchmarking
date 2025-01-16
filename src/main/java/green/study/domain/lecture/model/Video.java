package green.study.domain.lecture.model;

import green.study.domain.lecture.entity.VideoEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Video {

    private Long key;
    private String title;
    private String videoName;
    private String uniquePath;
    private Long chapterKey;

    public static Video from(VideoEntity entity) {
        return Video.builder()
                .key(entity.getKey())
                .title(entity.getTitle())
                .videoName(entity.getVideoName())
                .uniquePath(entity.getUniquePath())
                .chapterKey(entity.getChapterKey())
                .build();
    }

    public VideoEntity toEntity(Video video, Long chapterKey) {
        return VideoEntity.builder()
                .key(video.getKey())
                .title(video.getTitle())
                .videoName(video.getVideoName())
                .uniquePath(video.getUniquePath())
                .chapterKey(chapterKey)
                .build();

    }

}
