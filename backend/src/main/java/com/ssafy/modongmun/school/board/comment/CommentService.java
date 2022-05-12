package com.ssafy.modongmun.school.board.comment;

import com.ssafy.modongmun.school.board.Board;
import com.ssafy.modongmun.school.board.BoardRepository;
import com.ssafy.modongmun.school.board.comment.dto.CommentRegisterDto;
import com.ssafy.modongmun.user.User;
import com.ssafy.modongmun.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService  {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /** 댓글 작성 */
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

    /** 댓글 전체 조회 */
    public List<CommentRegisterDto> getCommentList() throws IOException {
        //여기일단이상해 댓글 전체 조회가 게시물 번호 없이 모든 댓글이 다 조회가 되고있음.
        List<Comment> commentList = commentRepository.findAll();
        List<CommentRegisterDto> commentRegisterDtoList = new ArrayList<>();
        //여기서 코멘트 넘버가 같은 경우만 더해줘야할것같은데
        for(Comment comment : commentList){
            CommentRegisterDto commentRegisterDto = CommentRegisterDto
                    .builder()
                    .postId(comment.getCommentId())
                    .userId(comment.getUser().getUserId())
                    .content(comment.getContent())
                    .build();
            commentRegisterDtoList.add(commentRegisterDto);
        }
        return commentRegisterDtoList;

    }

    /** 댓글 상세 조회 */
    // 의미를 모르겠음.

    /** 댓글 수정 */
    public void modifyComment(Long postId, Long CommentId, CommentRegisterDto commentRegisterDto ){
        User user = userRepository.findById(commentRegisterDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException(" wrong user! "));
        Board post = boardRepository.findById(commentRegisterDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException(" wrong postId! "));

        //유저 체크가 이뤄줘야 함.
        Comment comment = Comment.builder()
                .user(user)
                .postId(post)
                .commentId(commentRegisterDto.getCommentId())
                .content(commentRegisterDto.getContent())
                .build();
        commentRepository.save(comment);
    }

    /** 댓글 삭제 */
    public void deleteComment(Long postId, Long commentId, CommentRegisterDto dto){

//        //수정 : 게시물이 맞는지 검사 - 댓글 수정부터 하고 리팩토링 하는걸로.
////        if (postId != commentRepository.getById(postId)){
////            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////        }else{}
//    }
//      리팩토링 예정

        //초기 코드
        Comment comment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(comment);
    }


}
