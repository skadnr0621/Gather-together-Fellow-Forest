package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
import com.ssafy.modongmun.school.board.dto.PostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    public PostDto registerBoard(BoardRegisterDto boardRegisterDto){
        School school = schoolRepository.findById(boardRegisterDto.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal school ID !"));
        User user = userRepository.findById(boardRegisterDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Illegal user ID !"));

        Board board = Board.builder()
                .school(school)
                .user(user)
                .postId(boardRegisterDto.getPostId())
                .title(boardRegisterDto.getTitle())
                .content(boardRegisterDto.getContent())
                .build();
//        Post savedPost = boardRepository.save(board);
        Board savedBoard = boardRepository.save(board);
        System.out.println(savedBoard.getUser());

//        return PostDto.toDto(savedPost);
        return PostDto.toDto(savedBoard);
    }
}
