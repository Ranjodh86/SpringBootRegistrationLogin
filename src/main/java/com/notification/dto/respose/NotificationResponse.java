package com.notification.dto.respose;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long notificationId;
    private String title;
    private String body;
    private boolean isRead;
    private boolean isDeleivered;

}
