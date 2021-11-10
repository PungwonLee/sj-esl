package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility,Long> {
}
