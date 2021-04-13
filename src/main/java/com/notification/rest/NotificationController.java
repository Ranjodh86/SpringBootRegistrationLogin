package com.notification.rest;

import com.notification.dto.request.TokenRequest;
import com.notification.dto.respose.NotificationResponse;
import com.notification.entity.Notification;
import com.notification.entity.NotificationLog;
import com.notification.entity.User;
import com.notification.entity.UserToken;
import com.notification.repository.NotificationLogRepository;
import com.notification.repository.NotificationRepository;
import com.notification.repository.UserRepository;
import com.notification.repository.UserTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class NotificationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    NotificationLogRepository notificationLogRepository;

    @Autowired
    NotificationRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/update_token")
    public String updateToken(@RequestBody TokenRequest tokenRequest) {
        Optional<User> user = userRepo.findByUsername(tokenRequest.getUsername());
        logger.info("updateToken - user : {}", user.get());
        UserToken userToken = userTokenRepository.findByIpAndUserId(tokenRequest.getMacAddress(), user.get());
        logger.info("updateToken - userToken : {}", userToken);
        if (userToken != null) {
            userToken.setToken(tokenRequest.getToken());
            userToken.setModifiedDate(new Date());
            userTokenRepository.save(userToken);
        }

        return "Token updated";
    }

    @PostMapping("/notification_read")
    public String setNotificationRead(@RequestBody TokenRequest tokenRequest) {
        List<NotificationLog> notificationLogs = new ArrayList<>();
        Optional<User> user = userRepo.findByUsername(tokenRequest.getUsername());
        if(user.isPresent()){
            List<UserToken> userTokenList = user.get().getUserTokens();
            notificationLogs = notificationLogRepository.findAllByUserToken(userTokenList);
        }

        logger.info("setNotificationRead - notificationLogs : {}", notificationLogs);
        for (NotificationLog notificationLog : notificationLogs) {
            logger.info("setNotificationRead - notificationLog : {}", notificationLog);
            notificationLog.setIsRead(true);
            notificationLogRepository.save(notificationLog);

        }

        return "Notifications read";
    }

    @PostMapping("/get_notifications")
    public List<NotificationResponse> getNotifications(@RequestBody TokenRequest tokenRequest) {

        List<NotificationLog> notificationLogs = new ArrayList<>();
        Optional<User> user = userRepo.findByUsername(tokenRequest.getUsername());
        if(user.isPresent()){
            List<UserToken> userTokenList = user.get().getUserTokens();
            notificationLogs = notificationLogRepository.findAllByUserToken(userTokenList);
         }
        logger.info("getNotifications - notificationLogs : {}", notificationLogs);
        List<NotificationResponse> notificationResponses = new ArrayList<>();
        logger.info("getNotifications - notificationResponses : {}", notificationResponses);
        for (NotificationLog notificationLog : notificationLogs) {
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.setTitle(notificationLog.getNotification().getTitle());
            notificationResponse.setBody(notificationLog.getNotification().getBody());
            notificationResponse.setNotificationId(notificationLog.getNotification().getNotificationId());
            notificationResponse.setDeleivered(notificationLog.getIsDelivered());
            notificationResponse.setRead(notificationLog.getIsRead());
            logger.info("getNotifications - notificationResponse : {}", notificationResponse);
            notificationResponses.add(notificationResponse);
        }
        logger.info("getNotifications - notificationResponses : {}", notificationResponses);
        return notificationResponses;
    }

    @PostMapping("/notification_delivered")
    public String setNotificationDelivered(@RequestBody Long notificationId) {

        Optional<NotificationLog> notificationLog = notificationLogRepository.findById(notificationId);

        if (notificationLog.isPresent()) {
            NotificationLog notificationLog1 = notificationLog.get();
            logger.info("setNotificationDelivered - notificationLog.get() : {}", notificationLog1);
            notificationLog1.setIsDelivered(true);
            notificationLog1.setDeliveredDate(new Date());
            notificationLogRepository.save(notificationLog1);
        }
        return "Notification read";
    }

    @PostMapping("/create_notification")
    public String createNotification(@RequestBody Notification notification) {

        if (notification != null) {
            notificationRepository.save(notification);
        }
        return "Notification added";
    }


    @PostMapping("/add_token")
    public String addToken(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.getUsername();
        Optional<User> user = userRepo.findByUsername(username);
        logger.info("addToken - user : {}", user);
        if (user != null) {

            UserToken userToken = new UserToken();
            userToken.setToken(tokenRequest.getToken());
            userToken.setCreateDate(new Date());
            userToken.setModifiedDate(new Date());
            userToken.setDeviceType(tokenRequest.getDeviceType());
            userToken.setMacAddress(tokenRequest.getMacAddress());
            userToken.setOwningApp(tokenRequest.getOwningApp());
            userToken.setUser(user.get());
            logger.info("addToken - userToken : {}", userToken);
            userTokenRepository.save(userToken);
        }
        return "Token added";
    }

    @PostMapping("/remove_token")
    public String removeToken(@RequestBody String token) {
        UserToken userToken = userTokenRepository.findByToken(token);
        logger.info("User token found : {}", userToken);
        if (userToken != null) {
            userToken.setIsActive(false);
            userTokenRepository.save(userToken);
        }
        return "Token removed";
    }

}
