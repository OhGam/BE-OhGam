package chbbo.BEOhGam.controller;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.dto.NoteDTO;
import chbbo.BEOhGam.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public String noteIndex() {
        return "noteIndex";
    }

    @GetMapping("/list")
    public String noteList(Model model) {
        List<Note> notes = noteService.findAllNote();
        model.addAttribute("notes", notes);
        return "noteList";
    }

    @GetMapping("/write")
    public String noteWrite() {
        return "noteWriteForm";
    }

    @PostMapping("/write")
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

    @GetMapping("/{id}")
    public String noteDetail(@PathVariable Long id, Model model) {
        Note note = noteService.findNote(id);
        model.addAttribute("note", note);
        return "noteDetail";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        Note note = noteService.findNote(id);
        model.addAttribute("note", note);
        return "noteUpdateForm";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute NoteDTO noteDTO) {
        System.out.println(noteDTO);
        Note note = Note.toNote(noteDTO);
        System.out.println(note);
        noteService.save(note);
        return "redirect:/notes/list";
    }
}
