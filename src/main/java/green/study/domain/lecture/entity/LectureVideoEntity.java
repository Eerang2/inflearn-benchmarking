package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lecture_video")
public class LectureVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_video_key")
    private Long key;
    private String sectionTitle;
    private Long lectureId;
}
