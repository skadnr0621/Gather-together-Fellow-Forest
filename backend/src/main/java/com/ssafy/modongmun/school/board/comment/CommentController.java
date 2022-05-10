package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.comment.dto.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/board/posts/{post_id}/comments")
    public ResponseEntity<?> registerComment(@PathVariable (name="post_id")Long postId, @RequestBody CommentRegisterDto commentRegisterDto){
        if (postId != commentRegisterDto.getPostId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        commentService.registerComment(commentRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);

        // 1. 최우선 수정 : path variable 받는 부분 추가 요망 {post_id} ->  서비스 넘겨줘야하니깐 서비스도 수정
        // -> @PathVariable("postId")
    }
}
