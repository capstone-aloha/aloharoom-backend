package com.aloharoombackend.service;

import com.aloharoombackend.dto.AddCommentDto;
import com.aloharoombackend.dto.CommentDto;
import com.aloharoombackend.dto.EditCommentDto;
import com.aloharoombackend.model.*;
import com.aloharoombackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final NotificationRepository notificationRepository;
    private final CommunityBoardRepository communityBoardRepository;

    //댓글 추가
    @Transactional
    public CommentDto addComment(AddCommentDto addCommentDto) {
        User user = userRepository.findById(addCommentDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 존재하지 않습니다."));
        Board board = new Board();
        CommunityBoard communityBoard = new CommunityBoard();
        Comment homeComment;
        Integer flag = addCommentDto.getFlag();
        if(flag==0) {
            board = boardRepository.findById(addCommentDto.getBoardId())
                    .orElseThrow(() -> new IllegalArgumentException("찾는 게시물이 존재하지 않습니다."));
            homeComment = new Comment(user, board, addCommentDto);
        } else {
            communityBoard = communityBoardRepository.findById(addCommentDto.getBoardId())
                    .orElseThrow(() -> new IllegalArgumentException("찾는 커뮤니티 글이 존재하지 않습니다."));
            homeComment = new Comment(user, communityBoard, addCommentDto);
        }


        //대댓글이면 댓글 사용자 알림 (han님이 내 댓글에 답글을 남겼습니다.)
        if(addCommentDto.getTargetUserId() != addCommentDto.getUserId()) {
            Long targetUserId = addCommentDto.getTargetUserId();
            User targetUser = userRepository.findById(targetUserId)
                    .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 존재하지 않습니다."));
            //내 댓글 "안녕하세요..."에 xx님이 댓글을 남겼습니다.
            //1. homeCommentId로 쿼리 날린다. / 2. dto로 댓글 내용을 받아온다. (된다면 효율적)
            String targetContent = addCommentDto.getTargetContent();
            if (targetContent.length() > 11) {
                targetContent = targetContent.substring(0, 11);
                targetContent += "...";
            }
            String content = "내 댓글 \"" + targetContent + "\"에 " + user.getNickname() + "님이 댓글을 남겼습니다.";
            if(flag==0) notificationRepository.save(new Notification(targetUser, user.getProfileUrl(), board, content));
            else notificationRepository.save(new Notification(targetUser, user.getProfileUrl(), communityBoard, content));
        }

        //댓글, 대댓글이면 게시물 사용자 알림 (han님이 댓글을 남겼습니다.)
        Long writerId;
        String title;
        if(flag==0) {
            writerId = board.getUser().getId();
            title = board.getHome().getAddress(); //오류 터지나?
        }
        else {
            writerId = communityBoard.getUser().getId();
            title = communityBoard.getTitle();
        }
        if(writerId != addCommentDto.getTargetUserId()) { //댓글 주인이 작성자인 경우 위 알림만 발생
            if (addCommentDto.getUserId() != writerId) { //내 댓글에 알림 안 받도록
                User writer = userRepository.findById(writerId) //글쓴이
                        .orElseThrow(() -> new IllegalArgumentException("찾는 회원이 존재하지 않습니다."));
                //내 게시글 "안녕하세요..." 글에 xx님이 댓글을 남겼습니다.
                if (title.length() > 11) {
                    title = title.substring(0, 11);
                    title += "...";
                }
                String content = "내 게시글 \"" + title + "\" 글에 " + user.getNickname() + "님이 댓글을 남겼습니다.";
                if(flag==0) notificationRepository.save(new Notification(writer, user.getProfileUrl(), board, content));
                else notificationRepository.save(new Notification(writer, user.getProfileUrl(), communityBoard, content));
            }
        }

        Comment save = commentRepository.save(homeComment);

        //가져와서 변경감지 => 댓글 작성 시에만(대댓글X)
        if(save.getGroupId() == null) save.setGroupId(save.getId());
        return new CommentDto(save);
    }

    //댓글 조회
    public List<CommentDto> getHomeComment(Long boardId) {
        List<Comment> homeComments = commentRepository.findAllByBoardId(boardId);
        homeComments.stream()
                .forEach(hc -> userRepository.findById(hc.getUser().getId()));
        List<CommentDto> commentDtos = commentSort(homeComments);
        return commentDtos;
    }
    public List<CommentDto> getCommunityComment(Long boardId) {
        List<Comment> communityComments = commentRepository.findAllByCommunityBoardId(boardId);
        communityComments.stream()
                .forEach(cc -> userRepository.findById(cc.getUser().getId()));
        List<CommentDto> commentDtos = commentSort(communityComments);
        return commentDtos;
    }

    private List<CommentDto> commentSort(List<Comment> homeComments) {
        //layer1, layer2 분리 후 layer2 시간순 정렬하여 layer1에 끼워주기
        List<Comment> layer0 = new ArrayList<>();
        List<Comment> layer1 = new ArrayList<>();
        for (Comment homeComment : homeComments) {
            if(homeComment.getLayer() == 0) layer0.add(homeComment);
            else if(homeComment.getLayer() == 1) layer1.add(homeComment);
        }
        //시간순 정렬
        Collections.sort(layer1, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getCreatedDate().isBefore(o2.getCreatedDate())) return -1;
                else if(o1.getCreatedDate().isAfter(o2.getCreatedDate())) return 1;
                else return 0;
            }
        });

        List<CommentDto> commentDtos = new ArrayList<>();
        for(Comment homeComment : layer0) {
            commentDtos.add(new CommentDto(homeComment));
        }
        for (Comment hc2 : layer1) {
            for (CommentDto commentDto : commentDtos) {
                if(commentDto.getCommentId() == hc2.getGroupId()){
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
        Comment homeComment = commentRepository.findById(editCommentDto.getHomeCommentId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));
        homeComment.setContent(editCommentDto.getContent());
        return new CommentDto(homeComment);
    }

    //댓글 삭제
    @Transactional
    public CommentDto deleteComment(Long commentId) {
        Comment homeComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));

        //댓글 일 경우
        if(homeComment.getLayer() == 0) {
            //대댓글 있을 때
            List<Comment> recomments = commentRepository.findAllByGroupId(homeComment.getGroupId());
            if(recomments.size() != 1) {
                homeComment.setContent("삭제된 댓글입니다.");
                return new CommentDto(homeComment);
            }
        }
        //댓글(대댓글 없는), 대댓글이면 삭제
        commentRepository.delete(homeComment);
        return new CommentDto(homeComment);
    }

    @Transactional
    public void deleteByBoardId(Long boardId) {
        commentRepository.deleteByBoardId(boardId);
    }

    @Transactional
    public void deleteByCommunityBoardId(Long boardId) {
        commentRepository.deleteByCommunityBoardId(boardId);
    }


    public List<Comment> findMyComment(Long userId) {
        return commentRepository.findAllByUserId(userId);
    }
}
