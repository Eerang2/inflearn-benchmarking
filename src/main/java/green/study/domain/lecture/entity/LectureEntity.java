package green.study.domain.lecture.entity;

import green.study.domain.lecture.model.LectureImage;
import green.study.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "LECTURE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_key")
    private Long key;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}
