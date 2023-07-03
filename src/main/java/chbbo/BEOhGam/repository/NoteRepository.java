package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT distinct n FROM Note n JOIN FETCH n.text WHERE n.id = :id")
    Optional<Note> findNote(@Param("id") Long id);

    @Query("SELECT distinct n from Note n join FETCH n.text")
    Optional<List<Note>> findAllNote();

    List<Note> findAllByUploadAtBetween(LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);
}
