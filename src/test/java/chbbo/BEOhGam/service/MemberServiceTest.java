package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void findByUserIdTest() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("test");
        memberDTO.setPassword("test1234");
        memberDTO.setNickname("testtest");
        memberDTO.setUsername("테스트");
        memberDTO.setPhone("010-0000-0000");

        // when
        memberService.join(memberDTO);
        Member foundMember = memberService.findByUserId("test");

        // then
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getPhone()).isSameAs("010-0000-0000");
    }
}
