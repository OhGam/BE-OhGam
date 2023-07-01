package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
}
