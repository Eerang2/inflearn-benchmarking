package green.study.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture_video")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_key")
    private Long key;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String videoName;

    @Column(name = "video_unique_path", nullable = false)
    private String uniquePath;

    @Column(name = "chapter_key", nullable = false)
    private Long chapterKey;
}
