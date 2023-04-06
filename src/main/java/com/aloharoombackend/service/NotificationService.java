package com.aloharoombackend.service;

import com.aloharoombackend.dto.NotificationDto;
import com.aloharoombackend.model.Notification;
import com.aloharoombackend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return notifications.stream().map(notification -> new NotificationDto(notification))
                .collect(Collectors.toList());
    }
}
