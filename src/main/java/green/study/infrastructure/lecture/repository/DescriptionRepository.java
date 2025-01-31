package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.DescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {
}
