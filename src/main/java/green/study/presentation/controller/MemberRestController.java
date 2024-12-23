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
    public Member create(@RequestBody MemberReq.Create memberReq) {
        log.info("request: {}", memberReq.toString());
        if (!memberReq.getPassword().equals(memberReq.getConfirmPassword())) {
            throw new IllegalArgumentException("password not matched");
        }
        return memberService.signup(memberReq.toMember());
    }

    @PostMapping("/check-id/{userId}")
    public Boolean checkId(@PathVariable String userId) {
        return memberService.checkUserId(userId);
    }


}
