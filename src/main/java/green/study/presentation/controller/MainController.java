package green.study.presentation.controller;

import green.study.domain.member.LoginUser;
import green.study.domain.member.model.Member;
import green.study.infrastructure.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public String index(@CookieValue(value = "JWT_TOKEN", required = false) final String token,
                        Model model) {

        Member member = jwtUtil.getLoginUserFromAccessToken(token);
        log.debug(member.getMemberId());
        model.addAttribute("member", member);
        return "index";
    }
}
