package sj.sjesl.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ScheduleTable  {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ScheduleTable_id")
    private Long id;
    private String title;
    private String detail;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
