package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
