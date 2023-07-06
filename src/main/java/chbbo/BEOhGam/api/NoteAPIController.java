package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.dto.NoteDTO;
import chbbo.BEOhGam.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // 조회 api!
    // 모든 감사 노트 목록 조회하는 api
    @GetMapping("/findall")
    public ResponseEntity<List<NoteDTO>> findAll() {
        List<Note> notes = noteService.findAllNote();
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);
    }

    // 연, 월, 일을 받아 그 날짜에 적힌 노트를 조회하는 api
    @GetMapping("/find")
    public ResponseEntity<List<NoteDTO>> findByDate(@RequestParam int year, @RequestParam int month, @RequestParam int day) {
        List<Note> notes = noteService.findAllByUploadAt(LocalDate.of(year, month, day).atStartOfDay(),
                LocalDate.of(year, month, day).atTime(LocalTime.MAX));
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);
    }
}
