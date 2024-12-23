package green.study.domain.admin.model;

import green.study.domain.admin.entity.MemberEntity;
import green.study.domain.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String name;
    private MemberType type;

    public static Member from(MemberEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .password(entity.getPassword())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .memberId(memberId)
                .password(password)
                .name(name)
                .type(type)
                .build();

    }
}
