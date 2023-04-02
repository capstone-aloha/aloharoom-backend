package com.aloharoombackend.service;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.CommentDto;
import com.aloharoombackend.model.Board;
import com.aloharoombackend.model.HomeComment;
import com.aloharoombackend.model.User;
import com.aloharoombackend.repository.BoardRepository;
import com.aloharoombackend.repository.HomeCommentRepository;
import com.aloharoombackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeCommentService {
    private final HomeCommentRepository homeCommentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //댓글 추가
    @Transactional
    public CommentDto addComment(AddCommentDto addCommentDto) {
        User user = userRepository.findById(addCommentDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 존재하지 않습니다."));
        Board board = boardRepository.findById(addCommentDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
        HomeComment homeComment = new HomeComment(user, board, addCommentDto);

        return new CommentDto(homeCommentRepository.save(homeComment));
    }

    //댓글 조회
    public List<CommentDto> getComment(Long boardId) {
        List<HomeComment> homeComments = homeCommentRepository.findAllByBoardId(boardId);

        return homeComments.stream().map(homeComment -> new CommentDto(homeComment))
                .collect(Collectors.toList());
    }
}
