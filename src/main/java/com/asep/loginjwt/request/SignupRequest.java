package com.asep.loginjwt.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
}
