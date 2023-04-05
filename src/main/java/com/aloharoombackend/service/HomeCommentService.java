package com.aloharoombackend.service;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.CommentDto;
import com.aloharoombackend.dto.EditCommentDto;
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
        List<HomeComment> layer0 = new ArrayList<>();
        List<HomeComment> layer1 = new ArrayList<>();
        for (HomeComment homeComment : homeComments) {
            if(homeComment.getLayer() == 0) layer0.add(homeComment);
            else if(homeComment.getLayer() == 1) layer1.add(homeComment);
        }
        //시간순 정렬
        Collections.sort(layer1, new Comparator<HomeComment>() {
            @Override
            public int compare(HomeComment o1, HomeComment o2) {
                if(o1.getCreatedDate().isBefore(o2.getCreatedDate())) return -1;
                else if(o1.getCreatedDate().isAfter(o2.getCreatedDate())) return 1;
                else return 0;
            }
        });

        List<CommentDto> commentDtos = new ArrayList<>();
        for(HomeComment homeComment : layer0) {
            commentDtos.add(new CommentDto(homeComment));
        }
        for (HomeComment hc2 : layer1) {
            for (CommentDto commentDto : commentDtos) {
                if(commentDto.getHomeCommentId() == hc2.getGroupId()){
                    commentDto.getCommentDtos().add(new CommentDto(hc2));
                    break;
                }
            }
        }

        return commentDtos;
    }

    //댓글 수정
    @Transactional
    public CommentDto editComment(EditCommentDto editCommentDto) {
        HomeComment homeComment = homeCommentRepository.findById(editCommentDto.getHomeCommentId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));
        homeComment.setContent(editCommentDto.getContent());
        return new CommentDto(homeComment);
    }

    //댓글 삭제
    @Transactional
    public CommentDto deleteComment(Long commentId) {
        HomeComment homeComment = homeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));

        //댓글 일 경우
        if(homeComment.getLayer() == 0) {
            //대댓글 있을 때
            List<HomeComment> recomments = homeCommentRepository.findAllByGroupId(homeComment.getGroupId());
            if(recomments.size() != 1) {
                homeComment.setContent("삭제된 댓글입니다.");
                return new CommentDto(homeComment);
            }
        }
        //댓글(대댓글 없는), 대댓글이면 삭제
        homeCommentRepository.delete(homeComment);
        return new CommentDto(homeComment);
    }
}
