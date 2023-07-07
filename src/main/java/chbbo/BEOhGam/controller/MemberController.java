package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.exception.DuplicatedUserIdException;
import chbbo.BEOhGam.service.MemberService;
import chbbo.BEOhGam.validator.CheckUserIdValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
        return "redirect:/";
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

    @GetMapping("/")
    public String loginUser(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("info", userDetails.getUsername());
        return "loginUser";
    }

}
