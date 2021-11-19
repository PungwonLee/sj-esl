package sj.sjesl.facilityManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.sjesl.entity.Facility;
import sj.sjesl.repository.FacilityRepository;

@SpringBootTest
class FacilityServiceTest {

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private FacilityRepository facilityRepository;

    @Test
    void save() {
        String name ="광102";
        String building = "광개토대관";
        String floor = "1층";
        int capacity = 30;
        int cost = 0;
        String category = "강의실";

        FacilityRequestDto requestDto = new FacilityRequestDto(name, building, floor, capacity, cost, category);

        Long id = facilityService.save(requestDto);

        Facility facility = facilityRepository.findById(id).get();

        System.out.println(facility.getId());
        System.out.println(facility.getName());
        System.out.println(facility.getBuilding());

    }

    @Test
    void update() {
        Long id = Long.valueOf(2);
        String name ="광103";
        String building = "광개토대관";
        String floor = "1층";
        int capacity = 40;
        int cost = 0;
        String category = "강의실";

        FacilityRequestDto requestDto = new FacilityRequestDto(name, building, floor, capacity, cost, category);

        Long fid = facilityService.update(id, requestDto);

        Facility facility = facilityRepository.findById(fid).get();

        System.out.println(facility.getName());
        System.out.println(facility.getCapacity());
    }

    @Test
    void delete() {
        Long id = Long.valueOf(2);

        facilityService.delete(id);

    }
}