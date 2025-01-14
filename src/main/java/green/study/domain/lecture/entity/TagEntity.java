package green.study.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture_tag")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
