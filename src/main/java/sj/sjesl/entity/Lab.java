package sj.sjesl.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lab{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "lab_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String location;
    private String state;
    private LocalDateTime scheduleStartDate;
    private LocalDateTime scheduleEndDate;
    private String content;
    private String notice;

    @Builder
    public Lab(Member member, String location) {
        this.member = member;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", member=" + member +
                ", location='" + location + '\'' +
                ", state='" + state + '\'' +
                ", scheduleStartDate=" + scheduleStartDate +
                ", scheduleEndDate=" + scheduleEndDate +
                ", reason='" + content + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }

    public void noticeUpdate(String notice, String content, LocalDateTime scheduleStartDate,LocalDateTime scheduleEndDate){
        this.notice=notice;
        this.content=content;
        this.scheduleStartDate=scheduleStartDate;
        this.scheduleEndDate=scheduleEndDate;
    }
}
