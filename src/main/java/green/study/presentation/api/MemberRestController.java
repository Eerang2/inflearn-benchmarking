package green.study.presentation.api;

import green.study.application.member.MemberService;
import green.study.domain.member.model.Member;
import green.study.infrastructure.util.CookieUtil;
import green.study.presentation.dto.MemberReq;
import green.study.presentation.dto.MemberRes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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
    public  Map<String, String> loadPage(@PathVariable String section) {
        String resHtml;
        switch (section) {
            case "dashboard" -> {
                resHtml = "<p>Welcome to your dashboard! Here you can see your progress and activity.</p>";
                return Map.of("html", resHtml);
            }
            case "courses" -> {
                resHtml = "<p>You are enrolled in the following courses:</p><ul><li>JavaScript for Beginners</li><li>Advanced CSS Techniques</li><li>Mastering React</li></ul>";
                return Map.of("html", resHtml);
            }
            case "profile" -> {
                resHtml = "<p>Update your profile details here.</p>";
                return Map.of("html", resHtml);
            }
            case "settings" -> {
                resHtml = "<p>Manage your account settings and preferences.</p>";
                return Map.of("html", resHtml);
            }
            default -> {
                return null;
            }
        }
    }
}
