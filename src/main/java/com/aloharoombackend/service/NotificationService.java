package com.aloharoombackend.service;

import com.aloharoombackend.dto.NotificationDto;
import com.aloharoombackend.model.Notification;
import com.aloharoombackend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final BoardService boardService;
    private final CommunityBoardService communityBoardService;

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

    /* 안 읽은 알림 갯수 조회 */
    public Map getNotificationCount(Long loginUserId) {
        Map<String, Integer> map = new HashMap<>();
        final int[] count = {0};
        notificationRepository.findAllByUserId(loginUserId).stream()
                        .forEach(notification -> {
                            if(!notification.getIsCheck()) count[0]++;
                        });
        map.put("notificationCount", count[0]);
        return map;
    }

    //알림 확인
    @Transactional
    public String checkNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("찾는 알림이 없습니다.");
                });
        notification.check();
        return "알림 확인 완료";
    }
}
