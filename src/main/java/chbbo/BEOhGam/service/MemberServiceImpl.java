package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import chbbo.BEOhGam.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long join(MemberDTO memberDTO) {
        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .password(memberDTO.getPassword())
                .username(memberDTO.getUsername())
                .phone(memberDTO.getPhone())
                .nickname(memberDTO.getNickname())
                .build();

        return memberRepository.save(member).getId();
    }
}
