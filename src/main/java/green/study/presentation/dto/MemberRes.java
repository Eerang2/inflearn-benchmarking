package green.study.presentation.dto;

import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.enums.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRes {

    private Long key;
    private String token;
    private MemberType type;

    public static MemberRes from(MemberEntity member, String generateToken) {
        return MemberRes.builder()
                .key(member.getKey())
                .token(generateToken)
                .type(member.getType())
                .build();
    }


}
