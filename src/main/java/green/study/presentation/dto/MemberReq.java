package green.study.presentation.dto;

import green.study.domain.admin.model.Member;
import green.study.domain.enums.MemberType;
import lombok.*;

@Getter
public class MemberReq {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Create {

        private String memberId;
        private String password;
        private String confirmPassword;
        private String name;
        private MemberType type;

        public Member toAdmin() {
            return Member.builder()
                    .adminId(memberId)
                    .password(password)
                    .name(name)
                    .type(type)
                    .build();
        }
    }
}
