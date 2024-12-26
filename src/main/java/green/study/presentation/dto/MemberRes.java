package green.study.presentation.dto;

import green.study.domain.admin.entity.MemberEntity;
import green.study.domain.enums.MemberType;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Member;

@Getter
@Builder
public class MemberRes {

    private Long id;
    private String token;
    private MemberType type;

    public static MemberRes from(MemberEntity member, String generateToken) {
        return MemberRes.builder()
                .id(member.getId())
                .token(generateToken)
                .type(member.getType())
                .build();
    }


}
