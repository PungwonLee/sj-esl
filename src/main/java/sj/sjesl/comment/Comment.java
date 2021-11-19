package sj.sjesl.comment;

import lombok.*;
import sj.sjesl.entity.BaseEntity;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationInquiry;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="Id")
    private ReservationInquiry reservationInquiry;

    @ManyToOne
    @JoinColumn(name="memberId")
    private Member admin;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Comment(ReservationInquiry reservationInquiry, Member admin, String content) {
        this.reservationInquiry = reservationInquiry;
        this.admin = admin;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}