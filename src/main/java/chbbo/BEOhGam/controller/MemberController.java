package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm() {

        return "createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(MemberDTO memberDTO) {
        Long memberId = memberService.join(memberDTO);
        return "redirect:/members";
    }


}
