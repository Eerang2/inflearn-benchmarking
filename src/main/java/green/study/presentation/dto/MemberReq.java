package green.study.presentation.dto;

import green.study.domain.member.model.Member;
import green.study.domain.enums.MemberType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
public class MemberReq {

    @Getter
    @Builder
    @ToString
    public static class Create {

        @NotBlank(message = "아이디는 필수입니다.")
        private String memberId;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "비밀번호 확인은 필수입니다.")
        private String confirmPassword;

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull
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


    @Getter
    @Builder
    @ToString
    public static class Login {
        @NotBlank(message = "아이디는 필수입니다.")
        private String memberId;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotNull
        private MemberType type;

        public Member toMember() {
            return Member.builder()
                    .memberId(memberId)
                    .password(password)
                    .type(type)
                    .build();
        }
    }
}
