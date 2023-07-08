package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import chbbo.BEOhGam.dto.NoteDTO;
import chbbo.BEOhGam.dto.TextDTO;
import chbbo.BEOhGam.service.MemberService;
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
    private final MemberService memberService;


    // 조회 api!
    // 조회한 회원 로그인 아이디 와 작성자 회원 로그인 아이디, 노트의 작성 날짜를 받아 특정 노트를 조회하는 api
    // 만약 조회한 회원과 작성 회원 로그인 아이디가 다르다면 조회수 증가
    @GetMapping("/findNote")
    public ResponseEntity<NoteDTO> findNote(@RequestParam String searchUserId, @RequestParam String noteUserId,
                                            @RequestParam int year, @RequestParam int month, @RequestParam int day) {
        Note note = noteService.findAllByUserIdAndUploadAt(noteUserId, LocalDate.of(year, month, day).atStartOfDay(),
                LocalDate.of(year, month, day).atTime(LocalTime.MAX)).get(0);
        if (!(searchUserId.equals(noteUserId))) {
            note.setViews(note.getViews() + 1);
            noteService.save(note);
        }
        NoteDTO noteDTO = NoteDTO.toNoteDTO(note);
        return ResponseEntity.ok(noteDTO);
    }

    // 모든 감사 노트 목록 조회하는 api
    @GetMapping("/findAll")
    public ResponseEntity<List<NoteDTO>> findAll() {
        List<Note> notes = noteService.findAllNote();
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);
    }

    // 연, 월, 일을 받아 그 날짜에 적힌 노트를 조회하는 api
    @GetMapping("/findByDate")
    public ResponseEntity<List<NoteDTO>> findByDate(@RequestParam int startYear, @RequestParam int startMonth,
                                                    @RequestParam int startDay, @RequestParam int endYear,
                                                    @RequestParam int endMonth, @RequestParam int endDay) {
        List<Note> notes = noteService.findAllByUploadAt(LocalDate.of(startYear, startMonth, startDay).atStartOfDay(),
                LocalDate.of(endYear, endMonth, endDay).atTime(LocalTime.MAX));
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);
    }

    // 회원 로그인 아이디로 그 회원이 적은 모든 노트 조회 api
    @GetMapping("/findByUserId")
    public ResponseEntity<List<NoteDTO>> findByUserId(@RequestParam String userId) {
        List<Note> notes = noteService.findAllByUserId(userId);
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);
    }

    // 회원 로그인 아이디와 날짜로 적은 노트 조회 api
    @GetMapping("/findByUserIdAndDate")
    public ResponseEntity<List<NoteDTO>> findByUserIdAndDate(@RequestParam String userId, @RequestParam int startYear,
                                                             @RequestParam int startMonth, @RequestParam int startDay,
                                                             @RequestParam int endYear, @RequestParam int endMonth,
                                                             @RequestParam int endDay) {
        List<Note> notes = noteService.findAllByUserIdAndUploadAt(userId, LocalDate.of(startYear, startMonth, startDay).atStartOfDay(),
                LocalDate.of(endYear, endMonth, endDay).atTime(LocalTime.MAX));
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note note : notes) {
            noteDTOList.add(NoteDTO.toNoteDTO(note));
        }
        return ResponseEntity.ok().body(noteDTOList);

    }

    // 등록 api!
    // 회원 로그인 아이디를 받아 감사 노트를 작성하는 api
    @PostMapping("/write")
    public ResponseEntity<NoteDTO> write(@RequestParam String userId, @RequestBody NoteDTO noteDTO) {
        noteDTO.setLikes(0);
        noteDTO.setViews(0);
        Note note = Note.toNote(noteDTO);
        note.setMember(memberService.findByUserId(userId));
        noteService.save(note);
        noteDTO = NoteDTO.toNoteDTO(note);
        return ResponseEntity.ok().body(noteDTO);
    }


    // 수정 api!
    // 회원 로그인 아이디와 날짜를 받아 감사 노트를 작성하는 api
    @PostMapping("/edit")
    public ResponseEntity<NoteDTO> edit(@RequestParam String userId, @RequestParam int year, @RequestParam int month,
                                        @RequestParam int day, @RequestBody NoteDTO noteDTO) {
        Note note = noteService.findAllByUserIdAndUploadAt(userId, LocalDate.of(year, month, day).atStartOfDay(),
                LocalDate.of(year, month, day).atTime(LocalTime.MAX)).get(0);
        note.setIsPublic(noteDTO.getIsPublic());
        List<Text> text = new ArrayList<>();
        for (TextDTO textDTO : noteDTO.getText()) {
            text.add(Text.toText(textDTO));
        }
        note.setText(text);
        note.setUpdateAt(LocalDateTime.now());
        noteService.save(note);
        noteDTO = NoteDTO.toNoteDTO(note);
        return ResponseEntity.ok().body(noteDTO);
    }
}
