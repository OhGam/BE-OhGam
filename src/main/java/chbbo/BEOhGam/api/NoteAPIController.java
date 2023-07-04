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

    // 모든 감사 노트 목록 조회하는 api
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

    // 노트 id로 감사 노트 조회하는 api
    @GetMapping("/{id}")
    @ResponseBody
    public NoteDTO noteFind(@PathVariable Long id) {
        Note note = noteService.findNote(id);
        return NoteDTO.toNoteDTO(note);
    }

    // 연, 월, 일을 받아 그 날짜에 적힌 노트를 조회하는 api
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
