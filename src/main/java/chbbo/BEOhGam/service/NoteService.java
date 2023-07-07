package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteService {

    // Note를 DB에 저장
    void save(Note note);

    // Note를 Note id로 찾기
    Note findNote(Long id);

    // 저장된 모든 Note 찾기
    List<Note> findAllNote();

    // 두 시간대 사이에 적힌 모든 노트 찾기
    List<Note> findAllByUploadAt(LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);

    List<Note> findAllByUserIdAndUploadAt(String userId, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);

    List<Note> findAllByUserId(String userId);
}
