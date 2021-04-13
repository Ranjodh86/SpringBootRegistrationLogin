package com.notification.dto.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String username;

    private String password;

    private Set<String> app;

    private String userGroup;

    private Set<String> role;
}
