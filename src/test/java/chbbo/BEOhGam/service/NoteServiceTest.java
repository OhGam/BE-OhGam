package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    NoteService noteService;

    @Test
    @Transactional
    void saveAndFindNoteTest() {
        // given
        Note note = new Note();

        // when
        noteService.save(note);
        Note foundNote = noteService.findNote(note.getId());

        // then
        assertThat(foundNote).isNotNull();
        assertThat(foundNote).isSameAs(note);
    }
}
