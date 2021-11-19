package sj.sjesl.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FacilityDateRequestDto {
    private String facility;
    private LocalDate date;

    @Builder
    public FacilityDateRequestDto(String facility, LocalDate date) {
        this.facility = facility;
        this.date = date;
    }

}
