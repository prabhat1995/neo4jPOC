package com.medium.accessmanagement.entity;

import java.util.Arrays;

public class PersonResponse {

    private String status;
    private String success;
    private TempPerson[] data;

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

    public TempPerson[] getData() {
        return data;
    }

    public void setData(TempPerson[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "status='" + status + '\'' +
                ", success='" + success + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
