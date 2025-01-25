package green.study.presentation.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRes {

    private String fileName;
    private Path filePath;
}
