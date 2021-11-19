package sj.sjesl.repository;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.dto.ExcelFacilityDto;
import sj.sjesl.dto.ExcelSubjectDto;
import sj.sjesl.dto.MemberDto;
import sj.sjesl.entity.*;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class RepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReservationInquiryRepository reservationInquiryRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    FacilityRepository facilityRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    public void testMember() {
        Member member = new Member();
        memberRepository.save(member);
        LocalDateTime selectDate_1 ;
    //        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);
    }
//    @Test
//    public void 연습() {
////        LocalDateTime dateTime= LocalDateTime.now();
//        LocalDateTime selectDate_1 = LocalDateTime.of(2021,12,19,0,0);
//        LocalDateTime selectDate_2 = LocalDateTime.of(2021,12,19,23,59);
//
//
//        boolean bool[] = new boolean[31];
//        Arrays.fill(bool,true);
//
//        List<Reservation> reservations = em.createQuery("select r from Reservation  r where r.startDateTime <= :selectDate_2 and :selectDate_1 <=r.endTDateTime")
//                .setParameter("selectDate_1", selectDate_1)
//                .setParameter("selectDate_2", selectDate_2)
//                .getResultList();
//
//        int flag=0;
//        for ( Reservation r: reservations){
//
//            if( r.getEndTDateTime().getDayOfWeek() ==selectDate_1.getDayOfWeek()){
//                System.out.println(r.getEndTDateTime().toLocalTime());
//            }
//            else{
//
//            }
//        }
//    }
}
