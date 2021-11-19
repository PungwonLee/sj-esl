package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Lab;
import sj.sjesl.entity.Member;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab,Long> {
    List<Lab> findByMember(Member member);
}
