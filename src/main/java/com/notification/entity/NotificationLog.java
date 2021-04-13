package com.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_log")
public class NotificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_token")
    private UserToken userToken;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @Column(name = "sent_date")
    private Date sentDate;

    @Column(name = "delivered_date")
    private Date deliveredDate;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "is_delivered")
    private Boolean isDelivered;

}
