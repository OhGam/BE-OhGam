package chbbo.BEOhGam.dto;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.domain.Role;
import lombok.*;

import static chbbo.BEOhGam.domain.Role.ROLE_USER;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MemberDTO {

    private String userId;
    private String password;
    private String username;
    private String phone;
    private String nickname;
    private Role role;

    public static MemberDTO toMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(member.getUserId());
        memberDTO.setPassword(memberDTO.getPassword());
        memberDTO.setUsername(memberDTO.getUsername());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setNickname(member.getNickname());
        memberDTO.setRole(ROLE_USER);

        return memberDTO;
    }
}
