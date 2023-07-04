package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.dto.NoteDTO;
import chbbo.BEOhGam.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@AllArgsConstructor
public class NoteAPIController {

    private final NoteService noteService;

    @GetMapping("/list")
    @ResponseBody
    public List<NoteDTO> noteList() {
        List<Note> notes = noteService.findAllNote();
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return noteDTOList;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public NoteDTO noteFind(@PathVariable Long id) {
        Note note = noteService.findNote(id);
        return NoteDTO.toNoteDTO(note);
    }

    @GetMapping("/date/{year}-{month}-{day}")
    @ResponseBody
    public List<NoteDTO> noteFindByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        List<Note> notes = noteService.findAllByUploadAt(LocalDate.of(year, month, day).atStartOfDay(),
                LocalDate.of(year, month, day).atTime(LocalTime.MAX));
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return noteDTOList;
    }
}
