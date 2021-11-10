package sj.sjesl.inquiry;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sj.sjesl.entity.ReservationInquiry;

@Getter
@NoArgsConstructor
public class InquirySaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public InquirySaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public ReservationInquiry toEntity() {
        return ReservationInquiry.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}