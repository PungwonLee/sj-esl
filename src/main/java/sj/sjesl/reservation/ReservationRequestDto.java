package sj.sjesl.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class ReservationRequestDto {

    private Long memberId;
    private String facility;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;

    @Builder
    public ReservationRequestDto(Long memberId, String facility,
                                 LocalDateTime startTime, LocalDateTime endTime, String purpose) {

        this.memberId = memberId;
        this.facility = facility;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }
}
