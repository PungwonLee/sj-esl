package sj.sjesl.entity;

import lombok.*;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationInquiry extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "contact_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    private String author;

    @Column(length = 500, nullable = false) //생략해도 OK 기본값 외에 추가로 변경 필요한 옵션 있을 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime answerDate;
    private String answerContent;

    private LocalDateTime replyDate;
    private String replyDetails;

    public ReservationInquiry(String author, String title, String content, LocalDateTime answerDate, String answerContent, LocalDateTime replyDate, String replyDetails) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.answerDate = answerDate;
        this.answerContent = answerContent;
        this.replyDate = replyDate;
        this.replyDetails = replyDetails;
    }

    @Builder
    public ReservationInquiry(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
