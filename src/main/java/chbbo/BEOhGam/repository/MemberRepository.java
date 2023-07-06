package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 유효성 검사 - 중복 체크
     * @param userId 유저아이디
     * @return
     */
    boolean existsByUserId(String userId);

    Optional<Member> findByUserId(String userId);
}
