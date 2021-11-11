package sj.sjesl.repository;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.dto.ExcelReservationDto;
import sj.sjesl.dto.ExcelSubjectDto;
import sj.sjesl.entity.*;
import sj.sjesl.dto.ExcelFacilityDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
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
    ReservationRepository reservationRepository;

    @Test
    public void testMember() {
        Member member = new Member();
        memberRepository.save(member);

//        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);
    }

    @Test
    public void as() throws InvalidFormatException, IOException {

    }

    @Test
    public void excelFacilityAndSubject() throws InvalidFormatException, IOException {


        OPCPackage opcPackage = OPCPackage.open(new File("src\\main\\resources\\Fac.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);


        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelFacilityDto data = new ExcelFacilityDto();

            data.setName(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setBuilding(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setFloor(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setCategory(row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue());

            Facility facility = new Facility(data.getName(), data.getBuilding(), data.getFloor(), 0, 0, data.getCategory());
            facilityRepository.save(facility);


        }

            //  ==============================과목 삽입 ==============================
        em.flush();
        em.clear();
        opcPackage = OPCPackage.open(new File("src\\main\\resources\\subjectComputer.xlsx"));

        workbook = new XSSFWorkbook(opcPackage);


        worksheet = workbook.getSheetAt(0);
        for (int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelSubjectDto data = new ExcelSubjectDto();

            data.setMajor(row.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setClassroom(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setSubjectName(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setCompletionType(row.getCell(5,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setSemester(row.getCell(8,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setProfessor(row.getCell(13,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setLectureDate(row.getCell(14,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setRoom(row.getCell(15,Row.CREATE_NULL_AS_BLANK).getStringCellValue());

            Subject subject = new Subject(data.getMajor(),data.getClassroom(),data.getSubjectName(),data.getCompletionType(),data.getSemester(),data.getProfessor(),data.getLectureDate(),data.getRoom());

            subjectRepository.save(subject);

        }



        //  ==============================교양 삽입 ==============================
        em.flush();
        em.clear();
        opcPackage = OPCPackage.open(new File("src\\main\\resources\\liberalArtsClasses.xlsx"));

        workbook = new XSSFWorkbook(opcPackage);


        worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelSubjectDto data = new ExcelSubjectDto();

            data.setMajor(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setClassroom(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setSubjectName(row.getCell(4,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setCompletionType(row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setSemester(row.getCell(9,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setProfessor(row.getCell(12,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setLectureDate(row.getCell(13,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setRoom(row.getCell(14,Row.CREATE_NULL_AS_BLANK).getStringCellValue());

            Subject subject = new Subject(data.getMajor(),data.getClassroom(),data.getSubjectName(),data.getCompletionType(),data.getSemester(),data.getProfessor(),data.getLectureDate(),data.getRoom());

            subjectRepository.save(subject);
        }

        /////////////////////예약내역 삽입
        em.flush();
        em.clear();


        SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        //원하는 포맷 넣기.
        SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);





        opcPackage = OPCPackage.open(new File("src\\main\\resources\\Reservation_history.xlsx"));


         workbook = new XSSFWorkbook(opcPackage);


        worksheet = workbook.getSheetAt(0);


        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);
            if(row.getCell(0,Row.CREATE_NULL_AS_BLANK) ==null) break;
            ExcelReservationDto data = new ExcelReservationDto();

            data.setFacility(row.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
//            data.setStartDateTime(LocalDateTime.parse(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            data.setEndTDateTime(LocalDateTime.parse(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            data.setUsername(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setPurpose(row.getCell(4,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setMobile(row.getCell(5,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setReservationStatus(ReservationStatus.COMPLETION);



            try {
                Date date = recvSimpleFormat.parse(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getDateCellValue().toString());
                String strDate = tranSimpleFormat.format(date);
                data.setStartDateTime(LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                date = recvSimpleFormat.parse(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getDateCellValue().toString());
                strDate = tranSimpleFormat.format(date);
                data.setEndTDateTime(LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


            } catch (ParseException e) {
                e.printStackTrace();
            }


            Facility facilty = facilityRepository.findByName(data.getFacility());
            Reservation reservation = new Reservation(facilty,data.getStartDateTime(),data.getEndTDateTime(),data.getUsername(),data.getPurpose(),data.getMobile(),0,data.getReservationStatus());

            reservationRepository.save(reservation);
        }
    }




}