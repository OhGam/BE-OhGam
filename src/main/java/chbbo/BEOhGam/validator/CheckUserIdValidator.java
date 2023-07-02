package chbbo.BEOhGam.validator;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckUserIdValidator extends AbstractValidator<MemberDTO> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDTO dto, Errors errors) {
        if(memberRepository.existsByUserId(dto.getUserId())) {
            errors.rejectValue("userId", "유저아이디 중복 오류", "이미 사용중인 아이디입니다.");
        }
    }
}
