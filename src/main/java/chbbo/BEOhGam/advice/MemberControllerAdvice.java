package chbbo.BEOhGam.advice;

import chbbo.BEOhGam.api.MemberAPIController;
import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.ErrorDTO;
import chbbo.BEOhGam.exception.DuplicatedUserIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = MemberAPIController.class)
public class MemberControllerAdvice {

    @ExceptionHandler(DuplicatedUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO duplicateUserId(Exception e) {
        return new ErrorDTO(e.getMessage());
    }
}
