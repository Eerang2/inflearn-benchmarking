package green.study.presentation.controller;

import green.study.domain.member.LoginUser;
import green.study.domain.member.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String index(@LoginUser final Member member) {

        System.out.println("=======> ");
        System.out.println(member.getMemberId());
        return "index";
    }
}
