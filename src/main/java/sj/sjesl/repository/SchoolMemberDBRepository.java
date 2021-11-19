package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.SchoolMemberDB;

public interface SchoolMemberDBRepository extends JpaRepository<SchoolMemberDB,Long> {
        SchoolMemberDB findByMobile(String toNumber);
}
