package green.study.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "LECTURE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 활성화
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_key")
    private Long key;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String uniqueImageName;

    private int likeCount;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createTime;

    @Column(name = "image_path",nullable = false)
    private String imagePath;

    @Column(name = "member_key", nullable = false)
    private Long memberKey;




}
