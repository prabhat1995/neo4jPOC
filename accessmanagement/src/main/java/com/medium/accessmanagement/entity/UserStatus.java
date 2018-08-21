package com.medium.accessmanagement.entity;

import java.util.Arrays;

public class UserStatus {

    private String user_id;
    private String organization_id;
    private String[] role;
    private String status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "user_id='" + user_id + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", role=" + Arrays.toString(role) +
                ", status='" + status + '\'' +
                '}';
    }
}
