package sj.sjesl.facilityManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sj.sjesl.entity.Facility;
import sj.sjesl.repository.FacilityRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Transactional
    public Long save(FacilityRequestDto requestDto) {
        return facilityRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, FacilityRequestDto requestDto) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시설물이 없습니다. id=" + id));

        facility.update(requestDto.getName(),requestDto.getBuilding(), requestDto.getFloor(),
                requestDto.getCapacity(), requestDto.getCost(), requestDto.getCategory());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시설물이 없습니다. id=" + id));

        facilityRepository.delete(facility);
    }
}
