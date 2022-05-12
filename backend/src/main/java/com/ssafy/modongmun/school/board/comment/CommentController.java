package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.comment.dto.CommentRegisterDto;
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

    // 댓글 작성
    @PostMapping("/board/posts/{post_id}/comments")
    public ResponseEntity<?> registerComment(@PathVariable (name="post_id")Long postId, @RequestBody CommentRegisterDto commentRegisterDto){
        if (postId != commentRegisterDto.getPostId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentService.registerComment(commentRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 전체조회
    // 테이블 별로 조회가 안되고 댓글 테이블에서 전부 조회되고 있음. 
    @GetMapping("/board/posts/{post_id}/comments")
    public ResponseEntity<List<CommentRegisterDto>> getCommentList(@PathVariable("post_id") Long postId) throws Exception {
        List<CommentRegisterDto> commentRegisterDtoList = commentService.getCommentList();
        return new ResponseEntity<>(commentRegisterDtoList, HttpStatus.OK);
    }

    //댓글 상세 조회를 잘 몰라서 pass
    //댓글 수정
    @PatchMapping("/board/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<?> modifyComment(@PathVariable("post_id") Long postId,
                                           @PathVariable("comment_id") Long commentId,
                                           @RequestBody CommentRegisterDto commentRegisterDto){
        commentService.modifyComment(postId, commentId, commentRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 삭제
    // 수정, 삭제 시 글을 쓴 유저에게만 권한을 줘야함
    @DeleteMapping("/board/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable("post_id") Long postId,
                                        @PathVariable("comment_id") Long commentId,
                                           CommentRegisterDto commentRegisterDto) throws Exception{
        commentService.deleteComment(postId, commentId, commentRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
