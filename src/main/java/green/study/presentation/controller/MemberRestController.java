package green.study.presentation.controller;

import green.study.application.member.MemberService;
import green.study.domain.member.model.Member;
import green.study.presentation.dto.MemberReq;
import green.study.presentation.dto.MemberRes;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    public Member create(@RequestBody @Valid MemberReq.Create memberReq) {
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

    @PostMapping("/login")
    public void login(@RequestBody @Valid MemberReq.Login memberReq, HttpServletResponse response) {
        MemberRes memberRes = memberService.loginAndGenerateToken(memberReq.toMember());
        log.info("Token: {}", memberRes.getToken());
        response.addCookie(memberRes.getCookie());
    }

}
