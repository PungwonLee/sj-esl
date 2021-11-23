package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.ScheduleTable;

public interface ScheduleTableRepository extends JpaRepository<ScheduleTable,Long> {
}
