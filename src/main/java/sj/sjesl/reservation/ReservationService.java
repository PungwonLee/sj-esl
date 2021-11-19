package sj.sjesl.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.Reservation;
import sj.sjesl.repository.BuildingRepository;
import sj.sjesl.repository.FacilityRepository;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FacilityRepository facilityRepository;
    private final BuildingRepository buildingRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<String> getBuildingList() {
        return facilityRepository.findBuildingByCategory("강의실").stream()
                .map(String::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public String getBuildingImg(String building) {
        return buildingRepository.findImgByBuilding(building);
    }

    @Transactional
    public List<FacilityResponseDto> getFloorList(BuildingFloorRequestDto requestDto) {
        return facilityRepository.findByBuildingAndFloorAndCategory(requestDto.getBuilding(),
                        requestDto.getFloor(), "강의실")
                .stream()
                .map(FacilityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ReservationListResponseDto> getReservationList(FacilityDateRequestDto requestDto) {
        Facility facility = facilityRepository.findByName(requestDto.getFacility());
        LocalDateTime startDatetime = LocalDateTime.of(requestDto.getDate().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endDatetime = LocalDateTime.of(requestDto.getDate(), LocalTime.of(23, 59, 59));

        List<Reservation> reservations = reservationRepository
                .findAllByFacilityAndStartTimeBetween(facility, startDatetime, endDatetime);


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
//            String cls = n++ + "교시";
//            Boolean tf = timetable.get(t);
//
//            ReservationListResponseDto dto = new ReservationListResponseDto(cls, tf);
            ReservationListResponseDto dto = new ReservationListResponseDto(t, timetable.get(t));

            list.add(dto);
        }

        return list;
    }

    @Transactional
    public Long save(ReservationRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        Facility facility = facilityRepository.findByName(requestDto.getFacility());
        LocalDateTime startTime = requestDto.getStartTime();
        LocalDateTime endTime = requestDto.getEndTime();
        String purpose = requestDto.getPurpose();

        Reservation reservation = Reservation.builder()
                .member(member)
                .facility(facility)
                .startTime(startTime)
                .endTime(endTime)
                .purpose(purpose)
                .build();

        return reservationRepository.save(reservation).getId();
    }

    @Transactional
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();

        reservationRepository.delete(reservation);
    }
}