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
@Transactional(readOnly = true)
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
        LectureEntity savedLecture = lectureRepository.save(lecture.toEntity());
        TagEntity savedMainTag = lectureTagsRepository.save(LectureTags.toMainTagEntity(mainTag, savedLecture.getKey()));

        subTags.forEach(subTag ->
                lectureTagsRepository.save(LectureTags.toSubTagEntity(subTag, savedLecture.getKey(), savedMainTag.getKey()))
        );
    }

    @Transactional
    public void saveDescription(Description description) {
        descriptionRepository.save(description.toEntity());
    }

    @Transactional
    public Chapter saveChapter(String chapterName, long lectureKey) {
        Chapter chapter = new Chapter(chapterName, lectureKey);
        return Chapter.from(chapterRepository.save(chapter.toEntity()));
    }

    @Transactional
    public void saveVideo(Video video, Long chapterKey) {
        videoRepository.save(video.toEntity(video, chapterKey));
    }

    // 등록중인 강의키를 꺼내오는 메서드
    public Long findLatestLectureByMemberKey(Long memberKey) {
        return lectureRepository.findLatestLectureKeyByMemberKey(memberKey)
                .orElseThrow(() -> new RuntimeException("등록중에 에러가 발생했습니다."));
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll().stream()
                .map(Lecture::from)
                .collect(Collectors.toList());
    }

    public List<Lecture> getLecturesByTag(String mainTag) {
        return lectureTagsRepository.findByTagName(mainTag).stream()
                .flatMap(tag -> lectureRepository.findAllByKey(tag.getLectureKey()).stream().map(Lecture::from))
                .collect(Collectors.toList());
    }

    public List<LectureRes> getFreeLectures() {
        return recommendLectureRepository.findFreeLectures().stream()
                .map(LectureRes::from)
                .collect(Collectors.toList());
    }

    public List<LectureRes> getRecentLectures() {
        return recommendLectureRepository.findRecentLectures().stream()
                .map(LectureRes::from)
                .collect(Collectors.toList());
    }

}
