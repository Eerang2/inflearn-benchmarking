package green.study.presentation.controller;

import green.study.application.member.MemberService;
import green.study.domain.member.model.Member;
import green.study.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(@CookieValue(value = "JWT_TOKEN", required = false) final String token,
                        Model model) {

        Member member = jwtUtil.getLoginUserFromAccessToken(token);
        log.debug(member.getMemberId());
        model.addAttribute("member", member);
        return "index";
    }

    @GetMapping("/signup")
    public String signForm() {
        return "member/signup";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/mypage/{id}")
    public String myPageForm(@PathVariable Long id, Model model) {

        // security 로 url 접속 차단
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "member/mypage";
    }
}
