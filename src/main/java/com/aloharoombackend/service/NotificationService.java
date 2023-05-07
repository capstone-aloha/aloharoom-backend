package com.aloharoombackend.service;

import com.aloharoombackend.dto.BoardOneDto;
import com.aloharoombackend.dto.NotificationDto;
import com.aloharoombackend.model.Notification;
import com.aloharoombackend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final BoardService boardService;

    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    //알림 리스트 조회
    public List<NotificationDto> getNotificationList(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        //알림 내림차순(시간) 정렬
        Collections.sort(notifications, new Comparator<Notification>() {
            @Override
            public int compare(Notification o1, Notification o2) {
                if(o1.getCreatedDate().isBefore(o2.getCreatedDate())) return 1;
                else if(o1.getCreatedDate().isAfter(o2.getCreatedDate())) return -1;
                else return 0;
            }
        });
        return notifications.stream().map(notification -> new NotificationDto(notification))
                .collect(Collectors.toList());
    }

    //알림 확인
    @Transactional
    public BoardOneDto checkNotification(Long notificationId, Long loginUserId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("찾는 알림이 없습니다.");
                });
        //알림 확인
        notification.check();

        if()
        //해당 게시물 정보 조회
        return boardService.findOneNew(notification.getBoard().getId(), loginUserId);
    }
}
