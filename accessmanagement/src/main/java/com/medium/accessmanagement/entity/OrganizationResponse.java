package com.medium.accessmanagement.entity;

import java.util.Arrays;

public class OrganizationResponse {
    private String status;
    private String success;
    private TempOrganization[] data;

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

    public TempOrganization[] getData() {
        return data;
    }

    public void setData(TempOrganization[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrganizationResponse{" +
                "status='" + status + '\'' +
                ", success='" + success + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
