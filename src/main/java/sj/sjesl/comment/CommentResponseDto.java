package sj.sjesl.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private Long adminId;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.adminId = comment.getAdmin().getId();
        this.content = comment.getContent();
    }
}
