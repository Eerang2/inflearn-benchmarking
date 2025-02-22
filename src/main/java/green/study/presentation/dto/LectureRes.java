package green.study.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureRes {

    private Long key;
    private String title;
    private int price;
    private String imagePath;
    private String imageUniqueName;
    private String description;
    private Long memberKey;
}
