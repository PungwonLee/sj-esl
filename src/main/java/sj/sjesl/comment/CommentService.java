package sj.sjesl.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.ReservationInquiry;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.repository.ReservationInquiryRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ReservationInquiryRepository reservationInquiryRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public List<Comment> getCommentList(Long id){
        ReservationInquiry reservationInquiry = reservationInquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id=" + id));

        return commentRepository.findCommentsByReservationInquiry(reservationInquiry);
    }

    @Transactional
    public Long save(Long id, CommentSaveRequestDto requestDto) {
        Optional<ReservationInquiry> reservationInquiry = reservationInquiryRepository.findById(id);
        Optional<Member> admin = memberRepository.findById(requestDto.getAdminId());
        String content = requestDto.getContent();

        Comment comment = Comment.builder()
                .reservationInquiry(reservationInquiry.get())
                .admin(admin.get())
                .content(content)
                .build();

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public Long update(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));

        comment.update(content);

        return commentId;
    }

    public CommentResponseDto findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void delete(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));

        commentRepository.delete(comment);
    }
}
