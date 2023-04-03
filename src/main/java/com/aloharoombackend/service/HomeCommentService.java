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

import java.util.*;

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
        HomeComment save = homeCommentRepository.save(homeComment);
        //가져와서 변경감지 => 댓글 작성 시에만(대댓글X)
        if(save.getGroupId() == null) save.setGroupId(save.getId());
        return new CommentDto(save);
    }

    //댓글 조회
    public List<CommentDto> getComment(Long boardId) {
        List<HomeComment> homeComments = homeCommentRepository.findAllByBoardId(boardId);
        List<CommentDto> commentDtos = commentSort(homeComments);
        return commentDtos;
    }

    private List<CommentDto> commentSort(List<HomeComment> homeComments) {
        //layer1, layer2 분리 후 layer2 시간순 정렬하여 layer1에 끼워주기
        List<HomeComment> layer1 = new ArrayList<>();
        List<HomeComment> layer2 = new ArrayList<>();
        for (HomeComment homeComment : homeComments) {
            if(homeComment.getLayer() == 1) layer1.add(homeComment);
            else if(homeComment.getLayer() == 2) layer2.add(homeComment);
        }
        //시간순 정렬
        Collections.sort(layer2, new Comparator<HomeComment>() {
            @Override
            public int compare(HomeComment o1, HomeComment o2) {
                if(o1.getCreatedDate().isBefore(o2.getCreatedDate())) return -1;
                else if(o1.getCreatedDate().isAfter(o2.getCreatedDate())) return 1;
                else return 0;
            }
        });

        List<CommentDto> commentDtos = new ArrayList<>();
        for(HomeComment homeComment : layer1) {
            commentDtos.add(new CommentDto(homeComment));
        }
        for (HomeComment hc2 : layer2) {
            for (CommentDto commentDto : commentDtos) {
                if(commentDto.getId() == hc2.getGroupId()){
                    commentDto.getCommentDtos().add(new CommentDto(hc2));
                    break;
                }
            }
        }

        return commentDtos;
    }
}
