package sj.sjesl.comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Api(tags = "댓글 API")
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 리스트 조회")
    @GetMapping("/inquiry/{id}/comment/")    //모두 조회
    public List<Comment> getCommentList(@PathVariable Long id) {
        return commentService.getCommentList(id);
    }

    @ApiOperation(value = "댓글 등록")
    @PostMapping("/inquiry/{id}/comment")    //등록
    public Long save(@PathVariable Long id, @RequestBody CommentSaveRequestDto requestDto) {
        return commentService.save(id, requestDto);
    }

    @ApiOperation(value = "댓글 내용 변경")
    @PutMapping("/inquiry/{id}/comment/{commentId}")    //내용 변경
    public Long update(@PathVariable Long id, @PathVariable Long commentId, @RequestBody String requestDto) {
        return commentService.update(commentId, requestDto);
    }

    @ApiOperation(value = "댓글 조회")
    @GetMapping("/inquiry/{id}/comment/commentId")    //조회
    public CommentResponseDto findById(@PathVariable Long id, @PathVariable Long commentId) {
        return commentService.findById(commentId);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/inquiry/{id}/comment/{commentId}") //삭제
    public Long delete(@PathVariable Long id, @PathVariable Long commentId) {
        commentService.delete(commentId);
        return id;
    }
}
