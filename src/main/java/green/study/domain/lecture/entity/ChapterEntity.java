package green.study.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture_chapter")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_key")
    private Long key;

    private String chapter;

    @Column(name = "lecture_key", nullable = false)
    private Long lectureKey;

}
