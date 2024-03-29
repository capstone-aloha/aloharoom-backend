package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity getNotificationList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(notificationService.getNotificationList(principalDetails.getUser().getId()));
    }

    @GetMapping("/count")
    ResponseEntity getNotificationCount(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(notificationService.getNotificationCount(principalDetails.getUser().getId()));
    }

    @PostMapping("/{notificationId}")
    public ResponseEntity checkNotification(@PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationService.checkNotification(notificationId));
    }
}
