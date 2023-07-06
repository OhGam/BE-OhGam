package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface MemberService {

    Map<String, String> validateHandling(Errors errors);

    Long join(MemberDTO memberDTO);

    Long createNickname(MemberDTO memberDTO);

    List<Member> findAll();

    Member findByUserId(String userId);
}
