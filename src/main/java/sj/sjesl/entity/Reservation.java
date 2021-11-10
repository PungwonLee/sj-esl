package sj.sjesl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "name")
    private Facility facility;

    private LocalDateTime reservationDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String purpose;

    private int 인원수;

    private ReservationStatus reservationStatus;

}