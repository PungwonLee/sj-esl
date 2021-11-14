package sj.sjesl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "name")
    private Facility facility;


//    private LocalDateTime reservationDate;

    private LocalDateTime startDateTime;

    private LocalDateTime endTDateTime;

    private String username;

    private String purpose;

    private String mobile;

    private int 인원수;

    private ReservationStatus reservationStatus;

    public Reservation(Facility facility, LocalDateTime startDateTime, LocalDateTime endTDateTime, String username, String purpose, String mobile, int 인원수, ReservationStatus reservationStatus) {
        this.facility = facility;
        this.startDateTime = startDateTime;
        this.endTDateTime = endTDateTime;
        this.username = username;
        this.purpose = purpose;
        this.mobile = mobile;
        this.인원수 = 인원수;
        this.reservationStatus = reservationStatus;
    }

    public Reservation() {

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", facility=" + facility +
                ", startDateTime=" + startDateTime +
                ", endTDateTime=" + endTDateTime +
                ", username='" + username + '\'' +
                ", purpose='" + purpose + '\'' +
                ", mobile='" + mobile + '\'' +
                ", 인원수=" + 인원수 +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}