package sj.sjesl.dto;

import lombok.Data;
import sj.sjesl.entity.Facility;
import sj.sjesl.entity.ReservationStatus;

import java.time.LocalDateTime;

@Data
public class ExcelReservationDto {

    private String facility;

    private LocalDateTime startDateTime;

    private LocalDateTime endTDateTime;

    private String username;

    private String purpose;

    private String mobile;

    private int 인원수;

    private ReservationStatus reservationStatus;

}
