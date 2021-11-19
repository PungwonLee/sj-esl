package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.FacilitySchedule;

public interface FacilityScheduleRepository extends JpaRepository<FacilitySchedule,Long> {
}
