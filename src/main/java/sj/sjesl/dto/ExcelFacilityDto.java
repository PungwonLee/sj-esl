package sj.sjesl.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelFacilityDto {

    private String name;

    private String building;

    private String floor;

    private int capacity;

    private int cost;

    private String category;

    @Override
    public String toString() {
        return "ExcelData{" +
                "name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", capacity=" + capacity +
                ", cost=" + cost +
                ", category='" + category + '\'' +
                '}';
    }
}