package com.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_token")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userToken")
    private List<NotificationLog> notificationLogs;

    @Column
    private String token;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "owning_app")
    private String owningApp;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "is_active")
    private Boolean isActive;

}
