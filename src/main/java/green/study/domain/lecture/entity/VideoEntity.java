package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_key")
    private Long key;
}
