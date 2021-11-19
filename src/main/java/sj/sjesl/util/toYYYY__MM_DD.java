//package sj.sjesl.util;
//
//import lombok.Getter;
//import lombok.ToString;
//import sj.sjesl.entity.Reservation;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Getter
//@ToString
//public class Member202004GroupbyDTO {
//    private LocalDate yyyymmdd;
//    private Reservation.MemberStatus status;
//    private Long memberCount;
//
//    @QueryProjection // 추가
//    public Member202004GroupbyDTO(String yyyymmdd, Member202004.MemberStatus status, Long memberCount) {
//        this.yyyymmdd = LocalDate.parse(yyyymmdd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        this.status = status;
//        this.memberCount = memberCount;
//    }
//
//
//}