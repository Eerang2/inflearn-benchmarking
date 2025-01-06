package green.study.domain.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lecture_video")
public class LectureVideoEntity {

    @Id
    private Long key;
    private String sectionTitle;
    private Long lectureId;
}
