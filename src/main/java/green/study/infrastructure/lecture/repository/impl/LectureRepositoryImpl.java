package green.study.infrastructure.lecture.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import green.study.domain.lecture.entity.LectureEntity;
import green.study.infrastructure.lecture.repository.RecommendLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static green.study.domain.lecture.entity.QLectureEntity.lectureEntity;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements RecommendLectureRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LectureEntity> findFreeLectures() {
        return jpaQueryFactory
                .selectFrom(lectureEntity)
                .where(lectureEntity.price.eq(0))
                .orderBy(lectureEntity.likeCount.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<LectureEntity> findRecentLectures() {
        return jpaQueryFactory
                .selectFrom(lectureEntity)
                .orderBy(lectureEntity.createTime.desc())
                .limit(5)
                .fetch();
    }
}
