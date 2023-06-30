package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    TextRepository textRepository;

    @Test
    @Transactional
    void saveAndFindByIdTest() {
        // given
        Note note = new Note();  // 테스트용 노트 객체 생성

        // when
        noteRepository.save(note);  // noteRepository에 note 삽입
        Note foundNote = noteRepository.findById(note.getId()).get();  // noteRepository에서 note의 id로 조회

        // then
        assertThat(foundNote).isNotNull();  // foundNote에 Note가 담겨져 있는지
        assertThat(foundNote).isSameAs(note);  // 담긴 Note가 note와 같은지
    }

    @Test
    @Transactional
    void textAddTest() {
        // given
        Note note = new Note();
        Text text1 = new Text();
        text1.setContent("감사1");
        text1.setNote(note);
        Text text2 = new Text();
        text2.setContent("감사2");
        text2.setNote(note);
        Text text3 = new Text();
        text3.setContent("감사3");
        text3.setNote(note);
        List<Text> text = new ArrayList<>();
        text.add(text1);
        text.add(text2);
        text.add(text3);
        note.setText(text);

        // when
        textRepository.save(text1);
        textRepository.save(text2);
        textRepository.save(text3);
        noteRepository.save(note);
        Note foundNote = noteRepository.findById(note.getId()).get();

        // then
        for (int i = 0; i < foundNote.getText().size(); i++) {
            assertThat(foundNote.getText().get(i).getContent()).isEqualTo("감사" + (i+1));
        }
    }
}
