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
    // Note id로 Note-Text join Table에서 Note를 찾는 메서드
    // 그냥 findById를 안쓴 이유는 그렇게 찾으면 Note만 찾아와서 Text에 대한 정보를 받아올 수 없음
    @Query("SELECT distinct n FROM Note n JOIN FETCH n.text WHERE n.id = :id")
    Optional<Note> findNote(@Param("id") Long id);

    // 모든 Note 찾아오는 메서드
    @Query("SELECT distinct n from Note n join FETCH n.text")
    Optional<List<Note>> findAllNote();

    // minLocalDateTime과 maxLocalDateTime 사이에 적힌 모든 노트들을 가져오는 메서드
    @Query("SELECT distinct n from Note n join FETCH n.text where n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List<Note> findAllByUploadAtBetween(@Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                        @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId와 날짜로 노트들을 가져오는 메서드
    @Query("select distinct n FROM Note n join FETCH n.text where n.member.userId = :userId " +
            "and n.uploadAt between :minLocalDateTime and :maxLocalDateTime")
    List <Note> findAllByUserIdAndUploadAtBetween(@Param("userId") String userId,
                                                  @Param("minLocalDateTime") LocalDateTime minLocalDateTime,
                                                  @Param("maxLocalDateTime") LocalDateTime maxLocalDateTime);

    // userId로 노트들을 가져오는 메서드
    @Query("select distinct n From Note n join FETCH n.text where n.member.userId = :userId")
    List <Note> findAllByUserID(@Param("userId") String userId);
}
