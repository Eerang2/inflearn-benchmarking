package green.study.presentation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MemberController {

    @GetMapping("/member/signup")
    public String signForm() {
        return "member/signup";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "member/login";
    }
}
