package green.study.presentation.controller;

import green.study.application.member.MemberService;
import green.study.domain.lecture.enums.MainTags;
import green.study.domain.member.model.Member;
import green.study.domain.model.GetToken;
import green.study.domain.model.Token;
import green.study.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(@GetToken Token token, Model model) {
        if (token != null) {
            Member member = jwtUtil.getLoginUserFromAccessToken(token.getToken());
            model.addAttribute("member", member);
        }
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

    @GetMapping("/mypage/{key}")
    public String myPageForm(@PathVariable("key") Long key, Model model) {

        // security 로 url 접속 차단
        Member member = memberService.findByKey(key);
        model.addAttribute("member", member);
        model.addAttribute("type", member.getType().getValue());
        return "member/mypage";
    }

    @GetMapping("/create/banner")
    public String bannerForm(Model model) {
        // 대분류 전달
        model.addAttribute("mainCategories",
                Arrays.stream(MainTags.values())
                        .map(this::toCategoryMap)
                        .toList()
        );
        return "lecture/createBanner";
    }

    @GetMapping("/create/introduction")
    public String introduceForm() {
        return "lecture/createDescription";
    }

    @GetMapping("/create/videoupload")
    public String videoUploadForm() {
        return "lecture/createVideo";
    }

    private Map<String, String> toCategoryMap(MainTags category) {
        return Map.of(
                "name", category.name(),
                "displayName", category.getDisplayName()
        );
    }
}
