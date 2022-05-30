package com.ssafy.modongmun.school.board;

import com.ssafy.modongmun.school.School;
import com.ssafy.modongmun.school.SchoolRepository;
import com.ssafy.modongmun.school.board.dto.PostDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public PostDto registerBoard(PostDto postDto){
        School school = schoolRepository.findById(postDto.getSchoolId()).orElse(null);
        User user = userRepository.findById(postDto.getUserId()).orElse(null);

        Board board = Board.builder()
                .school(school)
                .user(user)
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
        boardRepository.save(board);

        return PostDto.toDto(board);
    }

    public List<PostDto> getBoardList(Long schoolId) throws IOException {
        List<Board> postList = boardRepository.findBySchool_schoolId(schoolId);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Board post : postList){
            PostDto postDto = PostDto.builder()
                    .postId(post.getPostId())
                    .schoolId(post.getSchool().getSchoolId())
                    .userId(post.getUser().getUserId())
                    .username(post.getUser().getUsername())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createDate(post.getCreateDate())
                    .build();
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    public PostDto getBoard(Long postId) {
        Board post = boardRepository.findById(postId).orElse(null);

        return PostDto.toDto(post);
    }

    @Transactional(rollbackFor = Exception.class)
    public PostDto modifyBoard(Long postId, PostDto postDto){
        Board board = boardRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Illegal post id!"));
        board.update(postDto);

        return PostDto.toDto(board);
    }

    @Transactional(rollbackFor = Exception.class)
    public PostDto deleteBoard(Long postId) throws IOException{
        Board board = boardRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Illegal board id!"));
        boardRepository.delete(board);

        return PostDto.toDto(board);
    }

}