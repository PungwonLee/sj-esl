package sj.sjesl.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuildingFloorRequestDto {
    private String building;
    private String floor;

    @Builder
    public BuildingFloorRequestDto(String building, String floor) {
        this.building = building;
        this.floor = floor;
    }

}
