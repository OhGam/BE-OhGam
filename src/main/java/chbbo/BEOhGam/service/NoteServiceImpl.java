package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final MemberService memberService;

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public List<Note> findAllNote() {
        Optional<List<Note>> findNotes = noteRepository.findAllNote();

        if (findNotes.isPresent()) {
            return findNotes.get();
        } else {
            System.out.println("노트가 존재하지 않습니다.");
            return null;
        }

    }

    @Override
    public List<Note> findAllByUploadAt(LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime) {
        return noteRepository.findAllByUploadAtBetween(minLocalDateTime, maxLocalDateTime);
    }

    @Override
    public List<Note> findAllByUserIdAndUploadAt(String userId, LocalDateTime minLocalDateTime,
                                                 LocalDateTime maxLocalDateTime) {
        return noteRepository.findAllByUserIdAndUploadAtBetween(userId, minLocalDateTime, maxLocalDateTime);
    }

    @Override
    public List<Note> findAllByUserId(String userId) {
        return noteRepository.findAllByUserID(userId);
    }

    @Override
    public void deleteNote(String userId, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime) {
        Member member = memberService.findByUserId(userId);
        noteRepository.deleteByMemberAndUploadAtBetween(member, minLocalDateTime, maxLocalDateTime);
    }

    @Override
    public void addLikeMemberToNote(String likeUserId, String noteUserId,
                                    LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime) {
        Long memberId = memberService.findByUserId(likeUserId).getId();
        noteRepository.addLikeMemberToNote(memberId, noteUserId, minLocalDateTime, maxLocalDateTime);
    }

    @Override
    public void removeLikeMemberFromNote(String cancleUserId, String noteUserId,
                                         LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime) {
        Long memberId = memberService.findByUserId(cancleUserId).getId();
        noteRepository.removeLikeMemberFromNote(memberId, noteUserId, minLocalDateTime, maxLocalDateTime);
    }
}
