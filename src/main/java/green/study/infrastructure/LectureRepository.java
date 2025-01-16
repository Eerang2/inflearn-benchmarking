package green.study.infrastructure;

import green.study.domain.lecture.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
    Optional<LectureEntity> findByMemberKey(Long memberKey);
}
