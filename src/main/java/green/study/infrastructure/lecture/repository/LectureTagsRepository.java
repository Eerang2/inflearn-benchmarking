package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureTagsRepository extends JpaRepository<TagEntity, Long> {
}
