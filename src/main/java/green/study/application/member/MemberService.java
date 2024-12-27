package green.study.application.member;

import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import green.study.infrastructure.jwt.JwtUtil;
import green.study.presentation.dto.MemberRes;
import jakarta.servlet.http.Cookie;
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
    public Member signup(Member member) {
        MemberEntity save = memberRepository.save(member.toEntity());
        return Member.from(save);
    }

    public boolean checkUserId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
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

        Cookie jwtCookie = new Cookie("JWT_TOKEN",accessToken);
        jwtCookie.setHttpOnly(true); // 클라이언트 스크립트 접근 방지
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60 * 24); // 1일

        return MemberRes.from(memberEntity, accessToken, jwtCookie);

    }
}
