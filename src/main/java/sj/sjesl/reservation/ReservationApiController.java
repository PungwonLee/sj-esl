package sj.sjesl.reservation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "예약 API")
@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private  ReservationService reservationService;

    @ApiOperation(value = "예약 가능한 건물 리스트 조회")
    @GetMapping("/reservation/building")     //예약가능한 강의실 건물 이름 리스트 리턴
    public List<String> getBuildingList() {
        return reservationService.getBuildingList();
    }

    @ApiOperation(value = "건물의 이미지 리턴")
    @PostMapping("/reservation/building/{building}")
    public String getBuildingImg(@PathVariable String building) {   //이미지 리턴 타입 바꿔야함
        return reservationService.getBuildingImg(building);
    }

    @ApiOperation(value = "건물 - 층 의 강의실 리스트 리턴")
    @PostMapping("/reservation/building/floor")
    public List<FacilityResponseDto> getFloorList(@RequestBody BuildingFloorRequestDto requestDto){
        return reservationService.getFloorList(requestDto);
    }

    @ApiOperation(value = "강의실 - 날짜 에 예약 가능한 시간 리스트 리턴")
    @PostMapping("/reservation/building/date")
    public List<ReservationListResponseDto> getReservationList(@RequestBody FacilityDateRequestDto requestDto) {
        return reservationService.getReservationList(requestDto);
    }

    @ApiOperation(value = "예약 등록")
    @PostMapping("/reservation/")
    public Long save(@RequestBody ReservationRequestDto requestDto){
        return reservationService.save(requestDto);
    }

    @ApiOperation(value = "예약 삭제")
    @DeleteMapping("/reservation/{id}")
    public Long delete(@PathVariable Long id) {
        reservationService.delete(id);
        return id;
    }
}
