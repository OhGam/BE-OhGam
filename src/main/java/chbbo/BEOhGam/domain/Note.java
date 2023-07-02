package chbbo.BEOhGam.domain;

import chbbo.BEOhGam.dto.NoteDTO;
import chbbo.BEOhGam.dto.TextDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Boolean isPublic;
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

    public static Note toNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setIsPublic(noteDTO.getIsPublic());
        note.setLikes(noteDTO.getLikes());
        note.setViews(noteDTO.getViews());
        note.setUploadAt(noteDTO.getUploadAt());
        note.setUpdateAt(noteDTO.getUpdateAt());
        List<Text> textList = new ArrayList<>();
        for (TextDTO textDTO : noteDTO.getText()) {
            textList.add(Text.toText(textDTO));
        }
        note.setText(textList);
        return note;
    }
}
