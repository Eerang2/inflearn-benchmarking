package green.study.presentation.dto;

import green.study.domain.admin.entity.MemberEntity;
import green.study.domain.enums.MemberType;
import lombok.Builder;
import lombok.Getter;
import jakarta.servlet.http.Cookie;

import java.lang.reflect.Member;

@Getter
@Builder
public class MemberRes {

    private Long id;
    private String token;
    private MemberType type;
    private Cookie cookie;

    public static MemberRes from(MemberEntity member, String generateToken, Cookie cookie) {
        return MemberRes.builder()
                .id(member.getId())
                .token(generateToken)
                .type(member.getType())
                .cookie(cookie)
                .build();
    }


}
