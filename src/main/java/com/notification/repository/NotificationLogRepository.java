package com.notification.repository;

import com.notification.entity.Notification;
import com.notification.entity.NotificationLog;
import com.notification.entity.User;
import com.notification.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    @Query( "SELECT notificationLog FROM NotificationLog notificationLog WHERE notificationLog.userToken in :ids" )
    List<NotificationLog> findAllByUserToken(@Param("ids") List<UserToken> userTokens);

    @Query("SELECT notificationLog FROM NotificationLog notificationLog WHERE notificationLog.userToken.token = ?1 and notificationLog.isDelivered=1 and notificationLog.isRead=0")
    List<NotificationLog> findAllIsDeliveredandNotReadByUserToken(String userToken);

}
