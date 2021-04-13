package com.notification.dto.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationLogRequest {

    private List<Long> notifications;

}
