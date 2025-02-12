package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {

    @Query("SELECT l.key FROM LectureEntity l WHERE l.memberKey = :memberKey ORDER BY l.createTime DESC LIMIT 1")
    Optional<Long> findLatestLectureKeyByMemberKey(@Param("memberKey") Long memberKey);

    List<LectureEntity> findAllByKey(Long memberKey);

}
