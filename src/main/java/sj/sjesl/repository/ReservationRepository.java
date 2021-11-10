package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
