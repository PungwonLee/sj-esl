package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sj.sjesl.entity.ReservationInquiry;

import java.util.List;

public interface ReservationInquiryRepository extends JpaRepository<ReservationInquiry,Long> {
    @Query("SELECT p FROM ReservationInquiry p ORDER BY p.id DESC")
    List<ReservationInquiry> findAllDesc();
}