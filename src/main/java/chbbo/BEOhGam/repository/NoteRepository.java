package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // 모든 Note 찾아오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text join fetch n.likeMember")
    Optional<List<Note>> findAllNote();

    // minLocalDateTime과 maxLocalDateTime 사이에 적힌 모든 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text join fetch n.likeMember where n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List<Note> findAllByUploadAtBetween(@Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                        @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId와 날짜로 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text join fetch n.likeMember where n.member.userId = :userId " +
            "and n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List <Note> findAllByUserIdAndUploadAtBetween(@Param("userId") String userId,
                                                  @Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                                  @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId로 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text t join fetch n.likeMember where n.member.userId = :userId")
    List <Note> findAllByUserID(@Param("userId") String userId);

    // userId 및 날짜를 받아 노트를 삭제하는 메서드
    @Transactional
    void deleteByMemberAndUploadAtBetween(Member member, LocalDateTime minLocalDateTime,
                                          LocalDateTime maxLocalDateTime);

    @Transactional
    default void addLikeMemberToNote(Long memberId, String noteUserId, LocalDateTime minLocalDateTime,
                                     LocalDateTime maxLocalDateTime) {
        Note note = findAllByUserIdAndUploadAtBetween(noteUserId, minLocalDateTime, maxLocalDateTime).get(0);
        if (note != null) {
            note.getLikeMember().add(memberId);
            save(note);
        }
    }

    @Transactional
    default void removeLikeMemberFromNote(Long memberId, String noteUserId, LocalDateTime minLocalDateTime,
                                                 LocalDateTime maxLocalDateTime) {
        Note note = findAllByUserIdAndUploadAtBetween(noteUserId, minLocalDateTime, maxLocalDateTime).get(0);
        if (note != null) {
            note.getLikeMember().remove(memberId);
            save(note);
        }
    }
}
