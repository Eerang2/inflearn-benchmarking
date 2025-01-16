package green.study.infrastructure;

import green.study.domain.lecture.entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
}
