package green.study.presentation.controller;

import green.study.application.member.MemberService;
import green.study.domain.admin.model.Admin;
import green.study.presentation.dto.AdminReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/create")
    public Admin create(@RequestBody AdminReq.Create adminReq) {
        if (adminReq.getPassword().equals(adminReq.getPasswordConfirm())) {
            throw new IllegalArgumentException("password not matched");
        }
        return memberService.signup(adminReq.toAdmin());
    }
}
