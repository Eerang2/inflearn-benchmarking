package green.study.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture_description")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dashboard_key")
    private Long key;

    @Column(nullable = false)
    private String content;

    @Column(name = "lecture_key", nullable = false, unique = true)
    private Long lectureKey;


}
