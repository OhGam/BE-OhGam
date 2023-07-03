package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteService {

    void save(Note note);

    Note findNote(Long id);

    List<Note> findAllNote();

    List<Note> findAllByUploadAt(LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);
}
