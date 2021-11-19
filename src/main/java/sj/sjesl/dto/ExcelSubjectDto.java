package sj.sjesl.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
public class ExcelSubjectDto {

    private String major;
    private String classroom;
    private String subjectName;
    private String completionType;
    private String semester;
    private String professor;
    private String lectureDate;
    private String room;
    public ExcelSubjectDto() {

    }

    public ExcelSubjectDto(String major, String classroom, String subjectName, String completionType, String semester, String professor, String lectureDate, String room) {
        this.major = major;
        this.classroom = classroom;
        this.subjectName = subjectName;
        this.completionType = completionType;
        this.semester = semester;
        this.professor = professor;
        this.lectureDate = lectureDate;
        this.room = room;
    }
}