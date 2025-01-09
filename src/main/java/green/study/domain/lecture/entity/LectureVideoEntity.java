package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lecture_video")
public class LectureVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_video_key")
    private Long key;

    private String chapter;

    @Column(name = "lecture_key", nullable = false)
    private Long lectureKey;

    @Column(name = "video_key", nullable = false)
    private Long videoKey;
}
