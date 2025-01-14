package green.study.infrastructure;

import green.study.domain.lecture.entity.DescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {
}
