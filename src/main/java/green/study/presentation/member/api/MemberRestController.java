package green.study.presentation.member.api;

import green.study.application.member.service.MemberService;
import green.study.infrastructure.util.CookieUtil;
import green.study.presentation.member.dto.MemberReq;
import green.study.presentation.member.dto.MemberRes;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/create")
    public void create(@RequestBody @Valid MemberReq.Create memberReq) {
        log.info("request: {}", memberReq.toString());
        memberService.signup(memberReq.toMember());
    }

    @PostMapping("/check-id/{userId}")
    public Boolean checkId(@PathVariable("userId") String userId) {
        return memberService.checkUserId(userId);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid MemberReq.Login memberReq, HttpServletResponse response) {
        MemberRes memberRes = memberService.loginAndGenerateToken(memberReq.toMember());
        log.info("Token: {}", memberRes.getToken());
        response.addCookie(CookieUtil.createJwtCookie(memberRes.getToken()));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue(value = "JWT_TOKEN", required = false) final String token,
                                 HttpServletResponse response) {
        response.addCookie(CookieUtil.deleteJwtCookie(token));
        return ResponseEntity.ok("logout");

    }
    @PostMapping("/mypage/{section}")
    public   MemberReq.Course loadPage(@PathVariable String section, Model model) {
        String resHtml;
        switch (section) {
            case "dashboard" -> {
                return MemberReq.Course.builder().bannerName("제목").bannerPath("e4b59221-01c1-4d11-908a-5bb55bb7f197_객실 1.png").price(100000).title("제목111").build();
            }
            case "courses" -> {
                return MemberReq.Course.builder().bannerName("제목").bannerPath("e4b59221-01c1-4d11-908a-5bb55bb7f197_객실 1.png").price(100000).title("제목111").build();
            }
            case "profile" -> {
                return MemberReq.Course.builder().bannerName("제목").bannerPath("e4b59221-01c1-4d11-908a-5bb55bb7f197_객실 1.png").price(100000).title("제목111").build();
            }
            case "settings" -> {
                return MemberReq.Course.builder().bannerName("제목").bannerPath("e4b59221-01c1-4d11-908a-5bb55bb7f197_객실 1.png").price(100000).title("제목111").build();
            }
            case "enroll" -> {
                return MemberReq.Course.builder().bannerName("제목").bannerPath("e4b59221-01c1-4d11-908a-5bb55bb7f197_객실 1.png").price(100000).title("제목111").build();
            }
            default -> {
                return null;
            }
        }
    }

}
