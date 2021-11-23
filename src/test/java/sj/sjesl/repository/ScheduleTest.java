package sj.sjesl.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.entity.*;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ScheduleTest {
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

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void 스케줄() {
        Map<String, Integer> ht = new HashMap<String, Integer>();
        Map<Integer, LocalDate> dayTable = new HashMap<Integer, LocalDate>();

        ht.put("월", 1);
        ht.put("화", 2);
        ht.put("수", 3);
        ht.put("목", 4);
        ht.put("금", 5);
        ht.put("토", 6);
        ht.put("일", 7);

        LocalDate localDate = LocalDate.now();
//        localDate.plusDays(1);

        //날짜 +1 한거에 추출해서 요일 해당하는 요일 숫자 구하기 / 1~7 월~일
        System.out.println(localDate.plusDays(1).getDayOfWeek().getValue());
        for (int i = 0; i < 7; i++) {
            int dayVal = localDate.plusDays(i).getDayOfWeek().getValue();
            dayTable.put(dayVal, localDate.plusDays(i));
        }

        for (Map.Entry<Integer, LocalDate> days : dayTable.entrySet()) {
            System.out.println(days.getValue() + ":" + days.getKey());
        }
//        Subject subject= subjectRepository.getById(17L);
        List<Subject> all = subjectRepository.findAll();

        for (Subject subject : all) {
            System.out.println(subject.toString());

            String str = subject.getLectureDate();
            String startTime = null;
            String endTime = null;
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i)))
                    list.add(ht.get(Character.toString(str.charAt(i))));

                if (Character.isDigit(str.charAt(i))) {

                    startTime = str.substring(i, i + 5);
                    endTime = str.substring(i + 6, i + 11);
                    LocalDateTime.of(localDate, LocalTime.of(Integer.parseInt(startTime.substring(0, 2)), Integer.parseInt(startTime.substring(3, 5))));
                    LocalTime startLocalTime = LocalTime.of(Integer.parseInt(startTime.substring(0, 2)), Integer.parseInt(startTime.substring(3, 5)));
                    LocalTime endLocalTime = LocalTime.of(Integer.parseInt(endTime.substring(0, 2)), Integer.parseInt(endTime.substring(3, 5)));
                    System.out.println(startLocalTime);
                    System.out.println(endLocalTime);
                    i = i + 11;

                    for (int j = 0; j < list.size(); j++) {
                        LocalDateTime startDateTime = LocalDateTime.of(dayTable.get(list.get(j)), startLocalTime);
                        LocalDateTime endDateTime = LocalDateTime.of(dayTable.get(list.get(j)), endLocalTime);
                        for (int k = 0; k < 4; k++) {
                            Reservation build = Reservation.builder()
                                    .startTime(startDateTime.plusWeeks(k))
                                    .endTime(endDateTime.plusWeeks(k))
                                    .notice("")
                                    .reservationName(subject.getSubjectName())
                                    .reservationStatus(ReservationStatus.COMPLETION)
                                    .subjectId(subject.getId())
                                    .build();
                            reservationRepository.save(build);
                        }

                    }
                    list.clear();
                }
            }
        }


    }
}
