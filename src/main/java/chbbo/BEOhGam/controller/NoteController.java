package chbbo.BEOhGam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {

    @GetMapping("/Note")
    public String noteIndex() {
        return "noteIndex";
    }
}
