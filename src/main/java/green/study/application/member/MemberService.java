package green.study.application.member;

import green.study.domain.admin.entity.MemberEntity;
import green.study.domain.admin.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import green.study.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public Member signup(Member admin) {
        MemberEntity save = memberRepository.save(admin.toEntity());
        return Member.from(save);
    }

    public boolean checkUserId(String userId) {
        return memberRepository.existsByAdminId(userId);
    }
}
