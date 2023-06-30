package chbbo.BEOhGam.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean isPublic;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Text> text;
    @Column
    private int likes;
    @Column
    private int views;
    @CreationTimestamp  // 작성 될 때 자동으로 날짜 입력
    @Column(updatable = false)  // 업데이트시 관여 X
    private LocalDateTime uploadAt;
    @UpdateTimestamp  // 수정 시간
    @Column(insertable = false)  // 첫 입력 시 관여 X
    private LocalDateTime updateAt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId")
//    private Member member;
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "commentId")
//    private Comment comments;
}
