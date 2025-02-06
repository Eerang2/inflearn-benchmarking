package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
    Optional<LectureEntity> findByMemberKey(Long memberKey);
    List<LectureEntity> findAllByKey(Long memberKey);

}
