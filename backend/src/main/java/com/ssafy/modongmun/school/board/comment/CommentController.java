package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/board/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> registerComment(@PathVariable (name="post_id")Long postId, @RequestBody CommentDto commentDto){
        CommentDto savedComment = commentService.registerComment(commentDto);
        return new ResponseEntity<CommentDto>(savedComment, HttpStatus.OK);
    }

    @GetMapping("/board/posts/{post_id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable("post_id") Long postId) throws Exception {
        List<CommentDto> commentDtoList = commentService.getCommentList(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @PatchMapping("/board/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable("post_id") Long postId,
                                           @PathVariable("comment_id") Long commentId,
                                           @RequestBody CommentDto commentDto){
        CommentDto modifiedComent = commentService.modifyComment(commentId, commentDto);
        return new ResponseEntity<>(modifiedComent, HttpStatus.OK);
    }

    @DeleteMapping("/board/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable("post_id") Long postId,
                                                    @PathVariable("comment_id") Long commentId) throws Exception{
        CommentDto deletedComment = commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }
}
