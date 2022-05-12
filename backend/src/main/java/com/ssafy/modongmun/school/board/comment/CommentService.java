package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.BoardRepository;
import com.ssafy.modongmun.school.board.comment.dto.CommentDto;
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
public class CommentService  {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public CommentDto registerComment(CommentDto commentDto){
        Board board = boardRepository.findById(commentDto.getPostId())
                .orElseThrow(()->new IllegalArgumentException("Illegal board id!"));
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(()->new IllegalArgumentException("Illegal user id!"));

        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .commentId(commentDto.getCommentId())
                .content(commentDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
        commentRepository.save(comment);

        return CommentDto.toDto(comment);
    }

    public List<CommentDto> getCommentList(Long postId) throws IOException {
        Board board = boardRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Illegal board id!"));
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment : commentList) {
            if(postId == comment.getBoard().getPostId()) {
                CommentDto commentDto = CommentDto.toDto(comment);
                commentDtoList.add(commentDto);
            }
        }

        return commentDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public CommentDto modifyComment(Long commentId, CommentDto commentDto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("Illegal comment id!"));
        comment.update(commentDto);

        return CommentDto.toDto(comment);
    }

    @Transactional(rollbackFor = Exception.class)
    public CommentDto deleteComment(Long postId, Long commentId){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(comment);

        return CommentDto.toDto(comment);
    }


}
