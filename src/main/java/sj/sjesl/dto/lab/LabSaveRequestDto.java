package sj.sjesl.dto.lab;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sj.sjesl.entity.Lab;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationInquiry;

@Getter
@NoArgsConstructor
public class LabSaveRequestDto {
    private Long memberId;
    private String location;
    @Builder
    public LabSaveRequestDto(Long memberId, String location) {
        this.memberId = memberId;
        this.location = location;
    }

    public Lab toEntity() {
        return Lab.builder()
                .location(location)
                .build();
    }

}