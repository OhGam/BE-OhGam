package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.dto.MessageDTO;
import chbbo.BEOhGam.service.MemberService;
import chbbo.BEOhGam.validator.CheckUserIdValidator;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberAPIController {

    private final MemberService memberService;

    @GetMapping("/list")
    public List<MemberDTO> memberList() {
        List<Member> members = memberService.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : members) {
            memberDTOList.add(MemberDTO.toMemberDTO(member));
        }
        return memberDTOList;
    }

    @PostMapping("/new")
    public ResponseEntity<MessageDTO> join (@Valid @RequestBody MemberDTO memberDTO) {
        memberService.join(memberDTO);
        MessageDTO message = new MessageDTO();
        message.setMessage("200");
        message.setData(memberDTO);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.checkUserIdDuplicate(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageDTO> loginSuccess(@Valid @RequestBody MemberDTO memberDTO) {
        MessageDTO message = new MessageDTO();
        message.setMessage("200");
        message.setData(memberDTO);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login?error=true")
    public ResponseEntity<MessageDTO> loginError(@Valid @RequestBody MemberDTO memberDTO) {
        MessageDTO message = new MessageDTO();
        message.setMessage("401");
        message.setData(memberDTO);

        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout/success")
    public ResponseEntity<MessageDTO> logoutSuccess(@Valid @RequestBody MemberDTO memberDTO) {
        MessageDTO message = new MessageDTO();
        message.setMessage("200");
        message.setData(memberDTO);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
