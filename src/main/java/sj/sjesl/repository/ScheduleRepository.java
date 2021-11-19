package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
