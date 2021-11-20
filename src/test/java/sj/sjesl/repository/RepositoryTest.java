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
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SchoolMemberDBRepository schoolMemberDBRepository;
    @Test
    public void testMember() {
        Member member = new Member();
        memberRepository.save(member);
        LocalDateTime selectDate_1;
        //        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);
    }
    @Test
    public void 연습() {

        SchoolMemberDB schoolMemberDB = new SchoolMemberDB(18013195L,"이풍원","01083629577",MemberPrivileges.STUDENT);
        SchoolMemberDB schoolMemberDB1 = new SchoolMemberDB(18013194L,"가","01012345678",MemberPrivileges.STUDENT);
        SchoolMemberDB schoolMemberDB2 = new SchoolMemberDB(18013196L,"아","01011111111",MemberPrivileges.STUDENT);
        SchoolMemberDB schoolMemberDB3 = new SchoolMemberDB(2020202L,"문현준","01033339999",MemberPrivileges.PROFESSOR);
        SchoolMemberDB schoolMemberDB4 = new SchoolMemberDB(9922922L,"권기학","01012345678",MemberPrivileges.PROFESSOR);
        schoolMemberDBRepository.save(schoolMemberDB);
        schoolMemberDBRepository.save(schoolMemberDB1);
        schoolMemberDBRepository.save(schoolMemberDB2);
        schoolMemberDBRepository.save(schoolMemberDB3);
        schoolMemberDBRepository.save(schoolMemberDB4);
    }
}
