package green.study.application.lecture.service;

import green.study.domain.lecture.entity.LectureEntity;
import green.study.domain.lecture.entity.TagEntity;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.enums.SubTags;
import green.study.domain.lecture.model.*;
import green.study.infrastructure.lecture.repository.*;
import green.study.presentation.dto.LectureRes;
import green.study.presentation.lecture.dto.LectureSubTagsRes;
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
    private final ChapterRepository chapterRepository;
    private final VideoRepository videoRepository;
    private final RecommendLectureRepository recommendLectureRepository;

    public List<LectureSubTagsRes> getSubCategories(MainTags mainCategory) {
        return Arrays.stream(SubTags.values())
                .filter(subTag -> subTag.getMainTags().equals(mainCategory))
                .map(subTag -> new LectureSubTagsRes(subTag.name(), subTag.getDisplayName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createBanner(Lecture lecture, MainTags mainTag, List<String> subTags) {
        LectureEntity save = lectureRepository.save(lecture.toEntity());
        TagEntity savedMainTag = saveMainTag(mainTag, save.getKey());
        for (String subTag : subTags) {
            saveSubTags(subTag, save.getKey(), savedMainTag.getKey());
        }
    }
    @Transactional
    public Chapter saveChapter(String chapterName, long lectureKey) {
        Chapter lectureVideo = Chapter.builder().chapter(chapterName).lectureKey(lectureKey).build();
        return Chapter.from(chapterRepository.save(lectureVideo.toEntity()));
    }

    @Transactional
    public void saveVideo(Video video, Long chapterKey) {
        videoRepository.save(video.toEntity(video, chapterKey));
    }

    @Transactional
    public Long findLectureByMemberKey(Long memberKey) {
        LectureEntity lectureEntity = lectureRepository.findByMemberKey(memberKey).orElseThrow(RuntimeException::new);
        return lectureEntity.getKey();
    }

    public final TagEntity saveMainTag(MainTags mainTag, long lectureKey) {
        return lectureTagsRepository.save(LectureTags.toMainTagEntity(mainTag, lectureKey));
    }

    public final void saveSubTags(String subTag, long lectureKey, long mainTagKey) {
        lectureTagsRepository.save(LectureTags.toSubTagEntity(subTag, lectureKey, mainTagKey));
    }

    public void saveDescription(Description description) {
        descriptionRepository.save(description.toEntity());
    }

    public List<LectureRes> getFreeLectures() {
        return recommendLectureRepository.findFreeLectures()
                .stream()
                .map(this::ToLectureRes)
                .collect(Collectors.toList());
    }

    public List<LectureRes> getRecentLectures() {
        return recommendLectureRepository.findRecentLectures()
                .stream()
                .map(this::ToLectureRes)
                .collect(Collectors.toList());
    }


    private LectureRes ToLectureRes(LectureEntity lectureEntity) {
        return new LectureRes(
                lectureEntity.getKey(),
                lectureEntity.getTitle(),
                lectureEntity.getPrice(),
                lectureEntity.getImagePath(),
                lectureEntity.getUniqueImageName(),
                lectureEntity.getDescription(),
                lectureEntity.getMemberKey()
        );
    }

}
