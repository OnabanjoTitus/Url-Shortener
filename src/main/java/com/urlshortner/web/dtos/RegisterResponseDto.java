package com.urlshortner.web.dtos;

import com.urlshortner.data.model.Status;

public class RegisterResponseDto {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
