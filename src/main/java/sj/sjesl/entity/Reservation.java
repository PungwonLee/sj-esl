package sj.sjesl.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sj.sjesl.entity.BaseEntity;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String purpose;

//    private ReservationStatus reservationStatus;


    @Builder
    public Reservation(Member member, Facility facility,
                       LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        this.member = member;
        this.facility = facility;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

}