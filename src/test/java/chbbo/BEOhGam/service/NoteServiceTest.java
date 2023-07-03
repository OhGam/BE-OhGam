package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        Text text1 = new Text();
        text1.setContent("hi");
        Text text2 = new Text();
        text2.setContent("hello");
        Text text3 = new Text();
        text3.setContent("ntmu");

        // when
        List<Text> text = new ArrayList<>();
        text.add(text1);
        text.add(text2);
        text.add(text3);
        note.setText(text);

        noteService.save(note);
        Note foundNote = noteService.findNote(note.getId());

        // then
        assertThat(foundNote).isSameAs(note);
        assertThat(foundNote.getText()).isSameAs(note.getText());
    }

    @Test
    @Transactional
    void findAllByUploadAtTest() {
        Note note1 = new Note();
        Note note2 = new Note();
        Text text = new Text();
        text.setContent("hi");
        List<Text> textList = new ArrayList<>();
        textList.add(text);
        note1.setText(textList);

        noteService.save(note1);
        noteService.save(note2);

        List<Note> foundNote = noteService.findAllByUploadAt(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
//        List<Note> foundNote = noteService.findAllByUploadAt(LocalDateTime.of(2023, 7, 4, 0, 0), LocalDateTime.of(2023, 7, 5, 0, 0));
        assertThat(note1).isIn(foundNote);
        assertThat(note2).isIn(foundNote);
    }
}
