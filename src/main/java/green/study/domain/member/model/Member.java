package green.study.domain.member.model;

import green.study.domain.member.exceptions.registers.MemberIdValidateException;
import green.study.domain.member.exceptions.registers.PasswordValidateException;
import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    private Long key;
    private String memberId;
    private String password;
    private String name;
    private MemberType type;

    public void validate() {
        if (!password.matches(".*\\d.*") || !password.matches(".*[A-Za-z].*")) {
            throw new PasswordValidateException("비밀번호는 숫자와 영문자, 특수문자를 포함해야합니다.");
        } else if (password.length() < 8 ) {
            throw new PasswordValidateException("비밀번호는 8자 이상입니다.");
        } else if (!memberId.matches("^[a-zA-Z0-9]*$")) {
            throw new MemberIdValidateException("아이디는 숫자와 영문자만 사용할수있습니다.");
        } else if (memberId.length() < 4 || memberId.length() > 12) {
            throw new MemberIdValidateException("아이디는 4자 이상 12자 이하입니다.");
        }
    }

    public static Member from(MemberEntity entity) {
        return Member.builder()
                .key(entity.getKey())
                .memberId(entity.getMemberId())
                .password(entity.getPassword())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .key(key)
                .memberId(memberId)
                .password(password)
                .name(name)
                .type(type)
                .build();

    }

    public Member(Long key, String memberId, MemberType type) {
        this.memberId = memberId;
        this.key = key;
        this.type = type;
    }
}
