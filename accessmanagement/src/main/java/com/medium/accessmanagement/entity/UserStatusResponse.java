package com.medium.accessmanagement.entity;

import java.util.Arrays;

public class UserStatusResponse {
    private String status;
    private String success;
    private UserStatus[] data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public UserStatus[] getData() {
        return data;
    }

    public void setData(UserStatus[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserStatusResponse{" +
                "status='" + status + '\'' +
                ", success='" + success + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
