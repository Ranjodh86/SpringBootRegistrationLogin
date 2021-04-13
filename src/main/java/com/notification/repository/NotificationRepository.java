package com.notification.repository;

import com.notification.entity.Notification;
import com.notification.entity.NotificationLog;
import com.notification.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query( "SELECT notification FROM Notification notification WHERE notification.id in :ids" )
    List<Notification> findAllByNotificationId(@Param("ids") List<Long> notificationIds);
}
