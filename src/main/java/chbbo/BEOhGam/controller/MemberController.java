package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.service.MemberService;
import chbbo.BEOhGam.validator.CheckUserIdValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private CheckUserIdValidator checkUserIdValidator;

    @GetMapping("/members/new")
    public String createForm() {

        return "createMemberForm";
    }

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUserIdValidator);
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberDTO memberDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("dto", memberDTO);

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "createMemberForm";
        }
        Long memberId = memberService.join(memberDTO);
        return "createNicknameForm";
    }

    @GetMapping("/members/new/nickname")
    public String createNicknameForm() {

        return "createNicknameMemberForm";
    }

    @PostMapping("/members/new/nickname")
    public String createNickname(@Valid MemberDTO memberDTO) {

        Long memberId = memberService.join(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {

        return "loginForm";
    }

}
