package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // 모든 Note 찾아오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text")
    Optional<List<Note>> findAllNote();

    // minLocalDateTime과 maxLocalDateTime 사이에 적힌 모든 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text where n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List<Note> findAllByUploadAtBetween(@Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                        @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId와 날짜로 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text where n.member.userId = :userId " +
            "and n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List <Note> findAllByUserIdAndUploadAtBetween(@Param("userId") String userId,
                                                  @Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                                  @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId로 노트들을 가져오는 메서드
    @Query("select distinct n from Note n join fetch n.member m join fetch n.text t where n.member.userId = :userId")
    List <Note> findAllByUserID(@Param("userId") String userId);

    // userId 및 날짜를 받아 노트를 삭제하는 메서드
    @Query("DELETE FROM Note n WHERE n.member.userId = :userId AND n.uploadAt BETWEEN :minLocalDateTime AND :maxLocalDateTime")
    void deleteNote(@Param("userId") String userId, @Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                    @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

}
