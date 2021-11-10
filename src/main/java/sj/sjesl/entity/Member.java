package sj.sjesl.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter @Setter
@NoArgsConstructor
public class Member implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String username;
    private String email;
    private String mobile;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberPrivileges privileges;

    @CreatedDate
    private LocalDateTime createDate;


//    private List<ReservationInquiry> reservationInquiries= new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Lab> labs= new ArrayList<>();



    public Member update(String name) {
        this.username =name;

        return this;
    }

    public Member(String username, String email, String mobile, String password) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    @Override
    public Long getId() {
        return this.memberId;
    }

    @Override
    public boolean isNew() {
        return createDate==null;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", privileges=" + privileges +
                ", createDate=" + createDate +
                '}';
    }
}
