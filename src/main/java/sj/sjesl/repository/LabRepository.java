package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Lab;

public interface LabRepository extends JpaRepository<Lab,Long> {
}
