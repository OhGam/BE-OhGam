package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class NoteController {

    NoteService noteService;

    @GetMapping("/notes")
    public String noteIndex() {
        return "noteIndex";
    }

    @GetMapping("/notes/write")
    public String noteWrite() {
        return "noteWriteForm";
    }

    @PostMapping("/notes/write")
    public String noteWrite(@ModelAttribute Note note) {
        if (note.getIsPublic() == null) {
            note.setIsPublic(true);
        } else {
            note.setIsPublic(false);
        }
        noteService.save(note);
        System.out.println("noteWrite success: " + note);
        return "redirect:/notes";
    }
}
