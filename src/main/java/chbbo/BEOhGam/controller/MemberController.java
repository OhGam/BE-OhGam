package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    public final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {

        return "/members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@ModelAttribute Member member) {

        memberService.save(member);
        return "redirect:/";
    }
}
