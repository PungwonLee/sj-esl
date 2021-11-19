package sj.sjesl.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByFacilityAndStartTimeBetween(Facility facility, LocalDateTime startDatetime, LocalDateTime endDatetime);
}
