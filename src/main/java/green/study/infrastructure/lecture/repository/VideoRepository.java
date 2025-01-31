package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
