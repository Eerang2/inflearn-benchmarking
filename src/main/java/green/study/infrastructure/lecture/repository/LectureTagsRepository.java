package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureTagsRepository extends JpaRepository<TagEntity, Long> {

    @Query("SELECT t FROM TagEntity t WHERE t.tagName = :tag")
    List<TagEntity> findByTagName(String tag);
}
