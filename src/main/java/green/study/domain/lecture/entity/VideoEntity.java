package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lecture_video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_key")
    private Long key;

    @Column(nullable = false)
    private String title;

    @Column(name = "video_unique_path", nullable = false)
    private String uniquePath;

    @Column(nullable = false)
    private String time;

    private String complete;
}
