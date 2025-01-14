package green.study.infrastructure;

import green.study.domain.lecture.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureTagsRepository extends JpaRepository<TagEntity, Long> {
}
