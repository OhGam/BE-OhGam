package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
