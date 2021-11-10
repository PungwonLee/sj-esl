package sj.sjesl.service;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sj.sjesl.dto.ExcelFacilityDto;
import sj.sjesl.entity.Facility;
import sj.sjesl.repository.FacilityRepository;

import java.io.File;
import java.io.IOException;


public class subjectTest {


    public static void main(String[] args) throws IOException, InvalidFormatException {

        final FacilityRepository facilityRepository = null;

        OPCPackage opcPackage = OPCPackage.open(new File("C:\\Users\\vnddn\\Downloads\\sj-esl\\src\\main\\resources\\Fac.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);



       // workbook = new XSSFWorkbook("Fac.xlsx");

        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelFacilityDto data = new ExcelFacilityDto();

            data.setName(row.getCell(1).getStringCellValue());
            data.setBuilding(row.getCell(2).getStringCellValue());
            data.setFloor(row.getCell(3).getStringCellValue());
            data.setCategory(row.getCell(6).getStringCellValue());

            Facility facility= new Facility(data.getName(),data.getBuilding(), data.getFloor(), 0,0, data.getCategory());
            facilityRepository.save(facility);

            System.out.println(data.toString() );
        }


    }
}