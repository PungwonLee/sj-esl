package sj.sjesl.inquiry;

import lombok.Getter;
import sj.sjesl.entity.ReservationInquiry;

import java.time.LocalDateTime;

@Getter
class InquiryListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public InquiryListResponseDto(ReservationInquiry entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getUpdateDate();
    }
}