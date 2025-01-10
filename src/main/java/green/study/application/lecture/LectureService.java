package green.study.application.lecture;

import green.study.domain.lecture.enums.MainTags;
import green.study.domain.lecture.enums.SubTags;
import green.study.presentation.dto.LectureSubTagsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LectureService {

    public List<LectureSubTagsRes> getSubCategories(MainTags mainCategory) {
        return Arrays.stream(SubTags.values())
                .filter(subTag -> subTag.getMainTags().equals(mainCategory))
                .map(subTag -> new LectureSubTagsRes(subTag.name(), subTag.getDisplayName()))
                .collect(Collectors.toList());
    }
}
