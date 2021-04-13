package com.notification.repository;

import com.notification.entity.User;
import com.notification.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    @Query("SELECT userToken FROM UserToken userToken WHERE userToken.macAddress = ?1")
    UserToken findByIp(String ip);

    @Query("SELECT userToken FROM UserToken userToken WHERE userToken.macAddress = ?1 and userToken.user = ?2")
    UserToken findByIpAndUserId(String ip, User user);

    @Query("SELECT userToken FROM UserToken userToken WHERE userToken.token = ?1")
    UserToken findByToken(String token);

}
