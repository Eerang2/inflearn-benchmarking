package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lecture_tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_key")
    private Long key;

    @Column(nullable = false)
    private String tagName;

    private Long parentsTagId;

    @Column(name = "lecture_key", nullable = false)
    private Long lectureKey;
}
