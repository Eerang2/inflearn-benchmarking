package green.study.application.lecture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FreeLectureListDto {

    private Long key;
    private String title;
    private int price;
    private String description;
    private String imagePath;
    private String imageName;
    private int likeCount;
    private Long memberKey;
}
