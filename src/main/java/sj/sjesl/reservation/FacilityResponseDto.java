package sj.sjesl.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sj.sjesl.entity.Facility;

@Getter
@NoArgsConstructor
public class FacilityResponseDto {

    private String name;
    private int capacity;

    public FacilityResponseDto(Facility entity) {
        this.name = entity.getName();
        this.capacity = entity.getCapacity();
    }

}

