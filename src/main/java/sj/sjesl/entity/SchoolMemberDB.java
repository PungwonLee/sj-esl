package sj.sjesl.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class SchoolMemberDB {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(name = "SchoolMemberDB_id")
    private Long SchoolMemberDB_id;

    private Long id;
    private String username;
    private String mobile;
    private String privilege;



}
