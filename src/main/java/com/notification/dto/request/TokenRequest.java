package com.notification.dto.request;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String username;
    private String token;
    private String owningApp;
    private String deviceType;
    private String macAddress;

}
