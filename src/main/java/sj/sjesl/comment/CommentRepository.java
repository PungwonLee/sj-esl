package sj.sjesl.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.ReservationInquiry;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByReservationInquiry(ReservationInquiry reservationInquiry);
}
