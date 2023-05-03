package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity getNotificationList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(notificationService.getNotificationList(userId));
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity checkNotification(@PathVariable Long notificationId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long loginUserId = principalDetails.getUser().getId();
        return ResponseEntity.ok(notificationService.checkNotification(notificationId, loginUserId));
    }
}
