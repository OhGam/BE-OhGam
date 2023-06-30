package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;

public interface NoteService {

    void save(Note note);

    Note findNote(Long id);
}
