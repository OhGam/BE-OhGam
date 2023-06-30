package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.repository.NoteRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Note findNote(Long id) {
        Optional<Note> foundNote = noteRepository.findById(id);

        if (foundNote.isPresent()) {
            return foundNote.get();
        } else {
            System.out.println("해당 노트는 존재하지 않습니다");
            return null;
        }
    }
}
