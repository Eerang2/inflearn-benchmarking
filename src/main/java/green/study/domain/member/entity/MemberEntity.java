package green.study.domain.member.entity;

import green.study.domain.member.enums.MemberType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMBER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_key")
    private Long key;


    @Column(nullable = false, unique = true, name = "member_id")
    @Size(min = 4, max = 12, message = "아이디는 4자 이상 12자 이하여야 합니다.")
    private String memberId;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{8,}$", message = "비밀번호는 최소 8자, 숫자와 문자를 포함해야 합니다.")
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType type;

}
