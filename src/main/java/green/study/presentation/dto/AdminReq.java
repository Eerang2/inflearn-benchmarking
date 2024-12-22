package green.study.presentation.dto;

import green.study.domain.admin.model.Admin;
import green.study.domain.enums.UserType;
import lombok.*;

public class AdminReq {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Create {

        private String adminId;
        private String password;
        private String confirmPassword;
        private String name;
        private UserType type;

        public Admin toAdmin() {
            return Admin.builder()
                    .adminId(adminId)
                    .password(password)
                    .name(name)
                    .type(type)
                    .build();
        }
    }
}
