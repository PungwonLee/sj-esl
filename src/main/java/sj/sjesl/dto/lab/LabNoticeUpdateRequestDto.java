package sj.sjesl.dto.lab;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LabNoticeUpdateRequestDto {
    private String notice;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public LabNoticeUpdateRequestDto(String notice, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.notice = notice;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
