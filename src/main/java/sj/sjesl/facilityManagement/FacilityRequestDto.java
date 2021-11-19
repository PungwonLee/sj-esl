package sj.sjesl.facilityManagement;

import lombok.Builder;
import lombok.Getter;
import sj.sjesl.entity.Facility;

@Getter
public class FacilityRequestDto {

    private String name;
    private String building;
    private String floor;
    private int capacity;
    private int cost;
    private String category;

    @Builder
    public FacilityRequestDto(String name, String building, String floor, int capacity, int cost, String category) {
        this.name = name;
        this.building = building;
        this.floor = floor;
        this.capacity = capacity;
        this.cost = cost;
        this.category = category;
    }

    public Facility toEntity(){
        return Facility.builder()
                .name(name)
                .building(building)
                .floor(floor)
                .capacity(capacity)
                .cost(cost)
                .category(category)
                .build();
    }

}
