package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
import com.ssafy.modongmun.school.board.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    //게시글 작성
    @PostMapping("/board/posts")
    public ResponseEntity<PostDto> registerBoard(@RequestBody BoardRegisterDto boardRegisterDto){
        PostDto savedPostDto = boardService.registerBoard(boardRegisterDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.OK);
    }

    //게시글 전체 조회
    @GetMapping("/board/posts")
    public ResponseEntity<List<BoardRegisterDto>> getBoardList() throws Exception {
        List<BoardRegisterDto> boardRegisterDtoList = boardService.getBoardList();
        return new ResponseEntity<>(boardRegisterDtoList, HttpStatus.OK);
    }

    //게시글 상세 조회
    @GetMapping("board/posts/{post_id}")
    public ResponseEntity<BoardRegisterDto> getBoard(@PathVariable("post_id") Long postId) throws Exception {
        BoardRegisterDto boardDto = boardService.getBoard(postId);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    //게시글 수정-?
    @PatchMapping("board/posts/{post_id}")
    public ResponseEntity<?> modifyPost(@PathVariable("post_id") Long postId, @RequestBody BoardRegisterDto boardRegisterDto){
        boardService.modifyBoard(postId, boardRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/board/posts/{post_id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("post_id") Long postId) throws Exception {
        boardService.deleteBoard(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
