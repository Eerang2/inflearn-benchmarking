package green.study.presentation.controller;

import green.study.application.member.MemberService;
import green.study.domain.admin.model.Member;
import green.study.presentation.dto.MemberReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/create")
    public Member create(@RequestBody MemberReq.Create adminReq) {
        log.info("request: {}", adminReq.toString());
        if (!adminReq.getPassword().equals(adminReq.getConfirmPassword())) {
            throw new IllegalArgumentException("password not matched");
        }
        return memberService.signup(adminReq.toMember());
    }

    @PostMapping("/check-id/{userId}")
    public Boolean checkId(@PathVariable String userId) {
        return memberService.checkUserId(userId);
    }

    @PostMapping("/login")
    public Member login(@RequestBody MemberReq.Create adminReq) {

    }

}
