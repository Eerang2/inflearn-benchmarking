package green.study.application.member;

import green.study.domain.member.exceptions.registers.MemberIdValidateException;
import green.study.domain.member.exceptions.registers.PasswordValidateException;
import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import green.study.infrastructure.util.JwtUtil;
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
    public void signup(Member member) {

        // id, password 유효성 검사
        member.validate();

        // 백엔드에서 ID 한번 더 조회
        if(memberRepository.existsByMemberId(member.getMemberId())) {
            throw new MemberIdValidateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member.toEntity());
    }

    public boolean checkUserId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Transactional
    public MemberRes loginAndGenerateToken(Member member) {

        // 사용자 조회
        MemberEntity memberEntity = memberRepository.findByMemberId(member.getMemberId())
                .orElseThrow(() -> new MemberIdValidateException("사용자를 찾을 수 없습니다."));

        // 비번 확인
        if (!memberEntity.getPassword().equals(member.getPassword())) {
            throw new PasswordValidateException("비밀번호 불일치 합니다.");
        }

        // 토큰 생성
        String accessToken = jwtUtil.createAccessToken(Member.from(memberEntity));

        return MemberRes.from(memberEntity, accessToken);
    }

    @Transactional
    public Member findById(Long id) {
        MemberEntity memberById = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return Member.from(memberById);
    }
}
