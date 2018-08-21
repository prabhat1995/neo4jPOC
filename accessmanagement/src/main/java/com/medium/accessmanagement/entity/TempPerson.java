package com.medium.accessmanagement.entity;

public class TempPerson {

    private String _id;
    private String personId;
    private String email;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TempPerson{" +
                "_id='" + _id + '\'' +
                ", personId='" + personId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
