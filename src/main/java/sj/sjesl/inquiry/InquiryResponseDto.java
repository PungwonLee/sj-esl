package sj.sjesl.inquiry;

import lombok.Getter;
import sj.sjesl.entity.ReservationInquiry;

@Getter
public class InquiryResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public InquiryResponseDto(ReservationInquiry entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}