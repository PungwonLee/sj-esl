package sj.sjesl.repository;

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
        Member member = new Member();
        memberRepository.save(member);

//        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);

        List<Member> resultList = em.createQuery("select m from Member m where m.username=:username", Member.class)
                .setParameter("username", "이풍원")
                .getResultList();

        for (Member member1 :  resultList){
            System.out.println("member = "+member1);
        }
        //Dto 로 했을때 ..
        List<MemberDto> resultList1 = em.createQuery("select new sj.sjesl.dto.MemberDto(m.memberId,m.username) from Member m where m.username=:username", MemberDto.class)
                .setParameter("username", "이풍원")
                .getResultList();
        
        // APi 페이징
        em.createQuery("select m from Member m order by m.memberId desc ", Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

    }



}