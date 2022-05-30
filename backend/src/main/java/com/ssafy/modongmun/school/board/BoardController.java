package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.board.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/posts")
    public ResponseEntity<PostDto> registerBoard(@RequestBody PostDto postDto){
        PostDto savedPostDto = boardService.registerBoard(postDto);
        return new ResponseEntity<PostDto>(savedPostDto, HttpStatus.OK);
    }

    @GetMapping("/board/posts")
    public ResponseEntity<List<PostDto>> getBoardList(@RequestParam("selectSchool") Long schoolId) throws Exception {
        List<PostDto> postDtoList = boardService.getBoardList(schoolId);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("board/posts/{post_id}")
    public ResponseEntity<PostDto> getBoard(@PathVariable("post_id") Long postId) throws Exception {
        PostDto postDto = boardService.getBoard(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @PatchMapping("board/posts/{post_id}")
    public ResponseEntity<PostDto> modifyPost(@PathVariable("post_id") Long postId, @RequestBody PostDto postDto){
        PostDto modifiedPost = boardService.modifyBoard(postId, postDto);
        return new ResponseEntity<PostDto>(modifiedPost, HttpStatus.OK);
    }

    @DeleteMapping("/board/posts/{post_id}")
    public ResponseEntity<PostDto> deleteBoard(@PathVariable("post_id") Long postId) throws Exception {
        PostDto deletedPost = boardService.deleteBoard(postId);
        return new ResponseEntity<PostDto>(deletedPost, HttpStatus.OK);
    }


}
