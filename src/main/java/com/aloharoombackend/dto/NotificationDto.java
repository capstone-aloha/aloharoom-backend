package com.aloharoombackend.dto;

import com.aloharoombackend.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long notificationId;
    private Long boardId;
    private String content;
    private String profileUrl;
    private String createdDate;
    private Integer flag;
    private Boolean isCheck;

    public NotificationDto(Notification notification) {
        this.notificationId = notification.getId();
        this.content = notification.getContent();
        this.profileUrl = notification.getProfileUrl();
        this.isCheck = notification.getIsCheck();
        if(notification.getBoard() != null) {
            this.boardId = notification.getBoard().getId();
            this.flag = 0;
        } else {
            this.boardId = notification.getCommunityBoard().getId();
            this.flag = 1;
        }

        LocalDateTime startDT = notification.getCreatedDate();
        LocalDateTime endDT = LocalDateTime.now();
        Period p = Period.between(startDT.toLocalDate(), endDT.toLocalDate());
        if(p.getDays()<=0) {
            Duration d = Duration.between(startDT.toLocalTime(), endDT.toLocalTime());
            if(d.toHours()<=0) { //X분 전
                if(d.toMinutes() == 0) this.createdDate = "방금 전";
                else this.createdDate = d.toMinutes() + "분 전";
            } else { //X시간 전
                this.createdDate = d.toHours() + "시간 전";
            }
        } else {
            //2023.XX.XX
            this.createdDate = startDT.toString().replace('-', '.').replace('T', ' ').substring(0, 10);
        }
    }
}
