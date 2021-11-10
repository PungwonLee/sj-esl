package sj.sjesl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FacilitySchedule {

    @Id @GeneratedValue
    @Column(name = "facility_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "facility_name")
    private Facility facility;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String purpose;


}