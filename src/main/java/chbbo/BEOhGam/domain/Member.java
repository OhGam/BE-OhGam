package chbbo.BEOhGam.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phone;

    @Column
    private String nickname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> note;
}