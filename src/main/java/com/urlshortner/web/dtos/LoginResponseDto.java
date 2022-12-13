package com.urlshortner.web.dtos;

import com.urlshortner.data.model.Status;
import com.urlshortner.data.model.UserType;

public class LoginResponseDto {
    private Status status;
    private UserType userType;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
