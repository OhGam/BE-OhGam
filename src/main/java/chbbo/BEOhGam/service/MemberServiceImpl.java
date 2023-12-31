package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.domain.Role;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long join(MemberDTO memberDTO) {

        memberRepository.findByUserId(memberDTO.getUserId()).ifPresent(
                a -> {
                    throw new DuplicateFormatFlagsException("이미 존재하는 아이디입니다.");
                }
        );

        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .password(memberDTO.getPassword())
                .username(memberDTO.getUsername())
                .phone(memberDTO.getPhone())
                .role(Role.ROLE_USER)
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

    @Override
    public Member findByUserId(String userId) {
        Optional<Member> foundMember = memberRepository.findByUserId(userId);
        if (foundMember.isPresent()) {
            return foundMember.get();
        } else {
            System.out.println("해당 회원이 존재하지 않습니다.");
            return  null;
        }
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Boolean checkUserIdDuplicate(String userId) {
        return memberRepository.existsByUserId(userId);
    }

}
