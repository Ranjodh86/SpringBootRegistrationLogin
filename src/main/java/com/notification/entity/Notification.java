package com.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column
    private String title;

    @OneToMany(mappedBy = "notification")
    private List<NotificationLog> notificationLogs;

    @Column
    private String body;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    @Column
    private String locale;

    @Column
    private Integer domain;

    @Column(name = "created_by_user")
    private Long createdByUserId;

    @Column(name = "created_by_app")
    private String createdByApp;

    @Column(name = "target_group")
    private String targetGroup;

    @Column(name = "target_user")
    private Long targetUser;

    @Column(name = "target_app")
    private String targetApp;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_sent")
    private Boolean isSent;


}
