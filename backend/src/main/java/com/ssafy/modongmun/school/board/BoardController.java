package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
import com.ssafy.modongmun.school.board.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/posts")
    public ResponseEntity<PostDto> registerBoard(@RequestBody BoardRegisterDto boardRegisterDto){
        PostDto savedPostDto = boardService.registerBoard(boardRegisterDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.OK);

    }

}
