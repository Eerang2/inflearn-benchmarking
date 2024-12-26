package green.study.application.member;

import green.study.domain.admin.entity.MemberEntity;
import green.study.domain.admin.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import green.study.infrastructure.jwt.JwtUtil;
import green.study.presentation.dto.MemberRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public Member signup(Member admin) {
        MemberEntity save = memberRepository.save(admin.toEntity());
        return Member.from(save);
    }

    public boolean checkUserId(String userId) {
        return memberRepository.existsByMemberId(userId);
    }

    @Transactional
    public MemberRes loginAndGenerateToken(Member member) {

        // 사용자 조회
        MemberEntity memberEntity = memberRepository.findByMemberId(member.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비번 확인
        if (!memberEntity.getPassword().equals(member.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치 합니다.");
        }

        // 토큰 생성
        String accessToken = jwtUtil.createAccessToken(Member.from(memberEntity));

        return MemberRes.from(memberEntity, accessToken);

    }
}
