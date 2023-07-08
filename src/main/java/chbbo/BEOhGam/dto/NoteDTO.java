package chbbo.BEOhGam.dto;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;
    private Boolean isPublic;
    private List<TextDTO> text;
    private String userId;
    private List<Long> likeMember;
    private int views;
    private LocalDateTime uploadAt;
    private LocalDateTime updateAt;

    public static NoteDTO toNoteDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setIsPublic(note.getIsPublic());
        noteDTO.setLikeMember(new ArrayList<>(note.getLikeMember()));
        noteDTO.setViews(note.getViews());
        noteDTO.setUploadAt(note.getUploadAt());
        noteDTO.setUpdateAt(note.getUpdateAt());
        noteDTO.setUserId(note.getMember().getUserId());
        List<TextDTO> textDTOList = new ArrayList<>();
        for (Text text : note.getText()) {
            textDTOList.add(TextDTO.toTextDTO(text));
        }
        noteDTO.setText(textDTOList);
        return noteDTO;
    }
}
