package sj.sjesl.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Reservation;
import sj.sjesl.repository.FacilityRepository;
import sj.sjesl.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void getBuildingList() {
        List<String> list = reservationService.getBuildingList();

        for (String a : list) {
            System.out.println(a);
        }
    }

    @Test
    void getBuildingImg() {
        String img = reservationService.getBuildingImg("광개토대관");

        System.out.println(img);
    }

    @Test
    void getFloorList() {
        BuildingFloorRequestDto requestDto = new BuildingFloorRequestDto("군자관", "3층");

        List<FacilityResponseDto> responseDtos = reservationService.getFloorList(requestDto);

        for (FacilityResponseDto a : responseDtos) {
            System.out.println(a.getName() + " " + a.getCapacity());
        }
    }

    @Test
    @Transactional
    void getReservationList() {
        String facility = "광102";
        LocalDate date = LocalDate.of(2021, 12, 8);

        FacilityDateRequestDto requestDto = new FacilityDateRequestDto(facility, date);


        Facility facility1 = facilityRepository.findByName(requestDto.getFacility());
        LocalDateTime startDatetime = LocalDateTime.of(requestDto.getDate().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endDatetime = LocalDateTime.of(requestDto.getDate(), LocalTime.of(23, 59, 59));

        List<Reservation> reservations = reservationRepository
                .findAllByFacilityAndStartTimeBetween(facility1, startDatetime, endDatetime);


        Map<LocalTime, Boolean> timetable = new TreeMap<>();
        LocalTime time = LocalTime.of(9, 00);

        for (int i = 0; i < 8; i++) {
            timetable.put(time, true);
            time = time.plusMinutes(90);
        }

        for (Reservation reservation : reservations) {
            LocalTime startTime = reservation.getStartTime().toLocalTime();
            LocalTime endTime = reservation.getEndTime().toLocalTime();
            time = startTime;

            while (time.isBefore(endTime)) {
                timetable.put(time, false);
                time = time.plusMinutes(90);
            }
        }

        List<ReservationListResponseDto> list = new ArrayList<>();
        int n = 1;
        for (LocalTime t : timetable.keySet()) {
//            System.out.println(t + " " + timetable.get(t));
//            String cls = n++ + "교시";
//            Boolean tf = timetable.get(t);
//            System.out.println(cls + " " + tf);
//            ReservationListResponseDto dto = new ReservationListResponseDto(cls, tf);
            ReservationListResponseDto dto = new ReservationListResponseDto(t, timetable.get(t));
            list.add(dto);
        }
    }

    @Test
    void save() {
        Long memberId = Long.valueOf(1);
        String facility = "광102";
        LocalDateTime startTime = LocalDateTime.parse("2021-12-08 15:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse("2021-12-08 21:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String purpose = "study";
        ReservationRequestDto requestDto = new ReservationRequestDto(memberId, facility, startTime, endTime, purpose);

        Long id = reservationService.save(requestDto);

        System.out.println(id);

        Reservation reservation = reservationRepository.findById(id).get();

        System.out.println(reservation.getId());
        System.out.println(reservation.getFacility().getName());
        System.out.println(reservation.getStartTime());
        System.out.println(reservation.getEndTime());
        System.out.println(reservation.getPurpose());
    }

    @Test
    void delete() {
        Long id = Long.valueOf(2);
        reservationService.delete(id);
    }
}