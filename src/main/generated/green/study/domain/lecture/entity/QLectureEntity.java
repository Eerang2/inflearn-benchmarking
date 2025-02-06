package green.study.domain.lecture.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureEntity is a Querydsl query type for LectureEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureEntity extends EntityPathBase<LectureEntity> {

    private static final long serialVersionUID = -2120065888L;

    public static final QLectureEntity lectureEntity = new QLectureEntity("lectureEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath imagePath = createString("imagePath");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Long> memberKey = createNumber("memberKey", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath uniqueImageName = createString("uniqueImageName");

    public QLectureEntity(String variable) {
        super(LectureEntity.class, forVariable(variable));
    }

    public QLectureEntity(Path<? extends LectureEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureEntity(PathMetadata metadata) {
        super(LectureEntity.class, metadata);
    }

}

