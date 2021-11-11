package sj.sjesl.repository;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.dto.ExcelFacilityDto;
import sj.sjesl.dto.ExcelSubjectDto;
import sj.sjesl.dto.MemberDto;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationInquiry;
import sj.sjesl.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
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

//        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);
    }
    @Test
    public void 연습() {
//        @Query("select m from Member m where m.username = ?1")
//        em.createQuery("select r from Reservation  r where r.startDateTime <= ");

    }



}