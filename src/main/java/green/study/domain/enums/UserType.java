package green.study.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    USER_TYPE("USER_TYPE"), ADMIN_TYPE("ADMIN_TYPE");
    private final String value;
}
