package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
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

    public void registerBoard(BoardRegisterDto boardRegisterDto){
        School school = schoolRepository.findById(boardRegisterDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(boardRegisterDto.getUserId()).orElse(null);

        Board board = Board.builder()
                .school(school)
                .user(user)
                .postId(boardRegisterDto.getPostId())
                .title(boardRegisterDto.getTitle())
                .content(boardRegisterDto.getContent())
                .build();
        boardRepository.save(board);
    }
}
