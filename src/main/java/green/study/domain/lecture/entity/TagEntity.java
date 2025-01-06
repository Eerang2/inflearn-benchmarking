package green.study.domain.lecture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TAGS")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @Column(nullable = false)
    private String tagName;

    private Long parentsTagId;
}
