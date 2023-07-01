package chbbo.BEOhGam.repository;

import chbbo.BEOhGam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
