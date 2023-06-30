package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

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
}
