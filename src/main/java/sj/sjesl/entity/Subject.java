package sj.sjesl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {
    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String major;
    @Column(name = "class")
    private String classroom;
    private String subjectName;
    private String completionType;
    private String semester;
    private String professor;
    private String lectureDate;
    private String room;

    public Subject(String major, String classroom, String subjectName, String completionType, String semester, String professor, String lectureDate, String room) {
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
