package green.study.application.member;

import green.study.domain.admin.entity.AdminEntity;
import green.study.domain.admin.model.Admin;
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

    public Admin signup(Admin admin) {
        AdminEntity save = memberRepository.save(admin.toEntity());
        return Admin.from(save);
    }

    public boolean checkUserId(String userId) {
        return memberRepository.existsByAdminId(userId);
    }
}
