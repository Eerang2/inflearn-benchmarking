package green.study.presentation.dto;

import green.study.domain.admin.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminReq {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private String adminId;
        private String password;
        private String passwordConfirm;

        public Admin toAdmin() {
            return Admin.builder()
                    .adminId(adminId)
                    .password(password)
                    .build();
        }
    }
}
