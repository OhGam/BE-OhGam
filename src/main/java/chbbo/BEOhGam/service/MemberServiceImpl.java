package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long join(MemberDTO memberDTO) {

        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .password(memberDTO.getPassword())
                .username(memberDTO.getUsername())
                .phone(memberDTO.getPhone())
                .build();

        return memberRepository.save(member).getId();
    }

    @Override
    public Long createNickname(MemberDTO memberDTO) {
        Member member = Member.builder()
                .nickname(memberDTO.getNickname())
                .build();

        return member.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
}
