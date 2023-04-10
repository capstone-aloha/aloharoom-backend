package com.aloharoombackend.service;

import com.aloharoombackend.dto.NotificationDto;
import com.aloharoombackend.model.Notification;
import com.aloharoombackend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }


    public List<NotificationDto> getNotification(Long userId) {
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
}
