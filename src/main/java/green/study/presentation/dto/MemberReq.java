package green.study.presentation.dto;

import green.study.domain.admin.model.Member;
import green.study.domain.enums.MemberType;
import lombok.*;

@Getter
public class MemberReq {

    @Getter
    @Builder
    @ToString
    public static class Create {

        private String memberId;
        private String password;
        private String confirmPassword;
        private String name;
        private MemberType type;

        public Member toMember() {
            return Member.builder()
                    .memberId(memberId)
                    .password(password)
                    .name(name)
                    .type(type)
                    .build();
        }
    }


}
