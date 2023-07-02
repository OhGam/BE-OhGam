package chbbo.BEOhGam.service;

import chbbo.BEOhGam.dto.MemberDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);

    Long join(MemberDTO memberDTO);
}
