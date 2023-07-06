package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.dto.MemberDTO;
import chbbo.BEOhGam.dto.MessageDTO;
import chbbo.BEOhGam.service.MemberService;
import chbbo.BEOhGam.validator.CheckUserIdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



}
