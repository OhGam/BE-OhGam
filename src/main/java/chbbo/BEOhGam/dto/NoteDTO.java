package chbbo.BEOhGam.dto;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;
    private Boolean isPublic;
    private List<TextDTO> text;
    private int likes;
    private int views;
    private LocalDateTime uploadAt;
    private LocalDateTime updateAt;

    public static NoteDTO toNoteDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setIsPublic(note.getIsPublic());
        noteDTO.setLikes(note.getLikes());
        noteDTO.setViews(note.getViews());
        noteDTO.setUploadAt(note.getUploadAt());
        noteDTO.setUpdateAt(note.getUpdateAt());
        List<TextDTO> textDTOList = new ArrayList<>();
        for (Text text : note.getText()) {
            textDTOList.add(TextDTO.toTextDTO(text));
        }
        noteDTO.setText(textDTOList);
        return noteDTO;
    }
}
