package com.ssafy.modongmun.school.board.comment;


import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.BoardRepository;
import com.ssafy.modongmun.school.board.comment.dto.CommentRegisterDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService  {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void registerComment(CommentRegisterDto commentRegisterDto){
        Board board = boardRepository.findById(commentRegisterDto.getPostId()).orElse(null);
        //commentRegisterDto.getPostId()
        User user = userRepository.findById(commentRegisterDto.getUserId()).orElse(null);

        Comment comment = Comment.builder()
                .postId(board)
                .user(user)
                .content(commentRegisterDto.getContent())
                .build();
        commentRepository.save(comment);
    }
}
