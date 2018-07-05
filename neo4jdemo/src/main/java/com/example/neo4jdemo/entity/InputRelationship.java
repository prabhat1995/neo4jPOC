package com.example.neo4jdemo.entity;


public class InputRelationship {

    private String groupName;
    private String route;
    private String personName;

    public String getGroupName() {
        return groupName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
