package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n JOIN FETCH n.text WHERE n.id = :id")
    Optional<Note> findNote(@Param("id") Long id);
}
