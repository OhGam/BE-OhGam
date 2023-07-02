package chbbo.BEOhGam.dto;

import lombok.*;

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
}
