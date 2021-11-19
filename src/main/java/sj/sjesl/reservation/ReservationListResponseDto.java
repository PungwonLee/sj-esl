package sj.sjesl.reservation;

import lombok.Getter;
import sj.sjesl.entity.Reservation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReservationListResponseDto {

//    private String 교시;
    private String 시간대;
    private Boolean 예약가능;

    public ReservationListResponseDto(LocalTime cls, Boolean tf) {
        this.시간대 = cls.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.예약가능 = tf;
    }
}
