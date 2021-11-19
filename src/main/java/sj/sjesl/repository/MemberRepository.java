package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sj.sjesl.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUsername(String name);
    Member findByMemberId(Long id);
    @Query("select m from Member m where m.username = ?1")
    Member findUsername(String name);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);

}
