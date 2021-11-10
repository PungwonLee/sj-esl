package sj.sjesl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lab{
    @Id @GeneratedValue
    @Column(name = "lab_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String location;
    private String state;
    private LocalDateTime scheduleStartDate;
    private LocalDateTime scheduleEndDate;
    private String reason;
    private String notice;
}
