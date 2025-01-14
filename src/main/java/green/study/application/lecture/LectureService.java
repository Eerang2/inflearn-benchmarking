package green.study.application.lecture;

import green.study.domain.lecture.entity.LectureEntity;
import green.study.domain.lecture.entity.TagEntity;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.enums.SubTags;
import green.study.domain.lecture.model.Description;
import green.study.domain.lecture.model.Lecture;
import green.study.domain.lecture.model.LectureTags;
import green.study.infrastructure.DescriptionRepository;
import green.study.infrastructure.LectureRepository;
import green.study.infrastructure.LectureTagsRepository;
import green.study.presentation.dto.LectureSubTagsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureTagsRepository lectureTagsRepository;
    private final DescriptionRepository descriptionRepository;

    public List<LectureSubTagsRes> getSubCategories(MainTags mainCategory) {
        return Arrays.stream(SubTags.values())
                .filter(subTag -> subTag.getMainTags().equals(mainCategory))
                .map(subTag -> new LectureSubTagsRes(subTag.name(), subTag.getDisplayName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createBanner(Lecture lecture, String mainTag, List<String> subTags) {
        LectureEntity save = lectureRepository.save(lecture.toEntity());
        TagEntity savedMainTag = saveMainTag(mainTag, save.getKey());
        for (String subTag : subTags) {
            saveSubTags(subTag, save.getKey(), savedMainTag.getKey());
        }
    }

    public final TagEntity saveMainTag(String mainTag, long lectureKey) {
        return lectureTagsRepository.save(LectureTags.toMainTagEntity(mainTag, lectureKey));
    }

    public final void saveSubTags(String subTag, long lectureKey, long mainTagKey) {
        lectureTagsRepository.save(LectureTags.toSubTagEntity(subTag, lectureKey, mainTagKey));
    }

    public void saveDescription(Description description) {
        descriptionRepository.save(description.toEntity());

    }
}
