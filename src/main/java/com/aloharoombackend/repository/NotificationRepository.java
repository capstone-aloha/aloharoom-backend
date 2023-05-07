package com.aloharoombackend.repository;

import com.aloharoombackend.model.Notification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @EntityGraph(attributePaths = {"user","board", "communityBoard"})
    List<Notification> findAllByUserId(Long userId);

    @Override
    @EntityGraph(attributePaths = {"board", "communityBoard"})
    Optional<Notification> findById(Long notificationId);
}
