package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.dto.BoardRegisterDto;
import com.ssafy.modongmun.school.board.dto.PostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    /** 게시글 등록 */
    public PostDto registerBoard(BoardRegisterDto boardRegisterDto){
        School school = schoolRepository.findById(boardRegisterDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(boardRegisterDto.getUserId()).orElse(null);

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

    /** 게시글 전체 조회 */
    public List<BoardRegisterDto> getBoardList() throws IOException {

        List<Board> postList = boardRepository.findAll();
        List<BoardRegisterDto> boardRegisterDtoList = new ArrayList<>();
        for(Board post : postList){
            BoardRegisterDto boardRegisterDto = BoardRegisterDto.builder()
                    .postId(post.getPostId())
                    .schoolId(post.getSchool().getSchoolId())
                    .userId(post.getUser().getUserId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();
            boardRegisterDtoList.add(boardRegisterDto);
        }
        return boardRegisterDtoList;
    }
    /** 게시글 상세 조회 */
    public BoardRegisterDto getBoard(Long postId) {

        Board post = boardRepository.findById(postId).orElse(null);
        return BoardRegisterDto.builder()
                .postId(post.getPostId())
                .schoolId(post.getSchool().getSchoolId())
                .userId(post.getUser().getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
//        BoardRegisterDto boardRegisterDto = BoardRegisterDto.builder()
//                .postId(post.getPostId())
//                .schoolId(post.getSchool().getSchoolId())
//                .userId(post.getUser().getUserId())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .build();
//        return boardRegisterDto;
    }

    /** 게시글 수정 */
    public void modifyBoard(Long postId, BoardRegisterDto boardRegisterDto){
        School school = schoolRepository.findById(boardRegisterDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(boardRegisterDto.getUserId()).orElse(null);

        if(postId == boardRegisterDto.getPostId()){
            Board board = Board.builder()
                    .school(school)
                    .user(user)
                    .postId(boardRegisterDto.getPostId())
                    .title(boardRegisterDto.getTitle())
                    .content(boardRegisterDto.getContent())
                    .build();
            boardRepository.save(board);
        }else{
            System.out.println("wrong postId !!!");
        }


    }

    /** 게시글 삭제 + 권한 관련 업데이트가 필요합니다. */
    public void deleteBoard(Long postId) throws IOException{
        Board delete_post = boardRepository.findById(postId).orElse(null);
        boardRepository.delete(delete_post); //null?
    }

}
