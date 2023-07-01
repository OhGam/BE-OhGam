package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/members/new")
    public String createForm() {
        return "/member/createMemberForm";
    }
}
