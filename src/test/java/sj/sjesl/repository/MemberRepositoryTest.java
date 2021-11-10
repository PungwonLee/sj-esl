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
import sj.sjesl.dto.ExcelSubjectDto;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationInquiry;
import sj.sjesl.dto.ExcelFacilityDto;
import sj.sjesl.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.File;
import java.io.IOException;

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
    @Test
    public void testMember() {
        Member member = new Member();
        memberRepository.save(member);

//        ReservationInquiry reservationInquiry = new ReservationInquiry(member, "안녕", "이거야");
//        reservationInquiryRepository.save(reservationInquiry);
    }

    @Test
    public void excelFacilityAndSubject() throws InvalidFormatException, IOException {


        OPCPackage opcPackage = OPCPackage.open(new File("C:\\Users\\vnddn\\Downloads\\sj-esl\\src\\main\\resources\\Fac.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);


        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelFacilityDto data = new ExcelFacilityDto();

            data.setName(row.getCell(1,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setBuilding(row.getCell(2,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setFloor(row.getCell(3,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            data.setCategory(row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue());

            System.out.println(data.toString());
            Facility facility = new Facility(data.getName(), data.getBuilding(), data.getFloor(), 0, 0, data.getCategory());
            facilityRepository.save(facility);


        }

            //  ==============================과목 삽입 ==============================


        opcPackage = OPCPackage.open(new File("C:\\Users\\vnddn\\Downloads\\sj-esl\\src\\main\\resources\\subjectComputer.xlsx"));

        workbook = new XSSFWorkbook(opcPackage);


        worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
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

            System.out.println(data.toString());
            Subject subject = new Subject(data.getMajor(),data.getClassroom(),data.getSubjectName(),data.getCompletionType(),data.getSemester(),data.getProfessor(),data.getLectureDate(),data.getRoom());

            subjectRepository.save(subject);

        }



        //  ==============================교양 삽입 ==============================

        opcPackage = OPCPackage.open(new File("C:\\Users\\vnddn\\Downloads\\sj-esl\\src\\main\\resources\\liberalArtsClasses.xlsx"));

        workbook = new XSSFWorkbook(opcPackage);


        worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
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

            System.out.println(data.toString());
            Subject subject = new Subject(data.getMajor(),data.getClassroom(),data.getSubjectName(),data.getCompletionType(),data.getSemester(),data.getProfessor(),data.getLectureDate(),data.getRoom());

            subjectRepository.save(subject);

        }

    }




}