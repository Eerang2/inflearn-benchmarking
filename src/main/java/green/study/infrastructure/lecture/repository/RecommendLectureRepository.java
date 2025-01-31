package green.study.infrastructure.lecture.repository;

import green.study.domain.lecture.entity.LectureEntity;

import java.util.List;

public interface RecommendLectureRepository {

    List<LectureEntity> findRandomFreeLectures();
}
