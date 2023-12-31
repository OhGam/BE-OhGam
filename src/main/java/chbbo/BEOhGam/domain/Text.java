package chbbo.BEOhGam.domain;

import chbbo.BEOhGam.dto.TextDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteId")
    private Note note;

    public static Text toText(TextDTO textDTO) {
        Text text = new Text();
        text.setId(textDTO.getId());
        text.setContent(textDTO.getContent());
        return text;
    }
}
