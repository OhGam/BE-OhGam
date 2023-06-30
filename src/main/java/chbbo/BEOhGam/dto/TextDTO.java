package chbbo.BEOhGam.dto;

import chbbo.BEOhGam.domain.Text;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TextDTO {

    private Long id;
    private String content;

    public static TextDTO toTextDTO(Text text) {
        TextDTO textDTO = new TextDTO();
        textDTO.setId(text.getId());
        textDTO.setContent(text.getContent());
        return textDTO;
    }
}
