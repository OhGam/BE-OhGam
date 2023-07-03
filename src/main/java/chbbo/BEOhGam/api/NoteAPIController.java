package chbbo.BEOhGam.api;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class NoteAPIController {

    private final NoteService noteService;

    @GetMapping("/api/notes/list")
    @ResponseBody
    public List<Note> noteList() {
        return  noteService.findAllNote();
    }
}
