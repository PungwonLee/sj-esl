package sj.sjesl.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Facility {

    @Id
    @GeneratedValue
    @Column(name = "facility_name")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String building;

    @Column(nullable = false)
    private String floor;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "facility")
    private List<Reservation> reservations = new ArrayList<>();

    public Facility(String name, String building, String floor, int capacity, int cost, String category) {
        this.name = name;
        this.building = building;
        this.floor = floor;
        this.capacity = capacity;
        this.cost = cost;
        this.category = category;
    }

    public Facility() {

    }
}