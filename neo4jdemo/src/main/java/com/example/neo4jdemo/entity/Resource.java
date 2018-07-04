package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    private String route;

    @JsonIgnoreProperties("hasAccessTo")
    @Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
    public Set<Access> hasAccessTo;

    public Resource(){
        hasAccessTo = new HashSet<>();
    }
    public String getRoute() {
        return route;
    }


    public void setRoute(String route) {
        this.route = route;
    }

    public void setAccess(Access access){
        hasAccessTo.add(access);
    }

    public boolean deleteAccess(Access access){
        if(hasAccessTo.contains(access)){
           return hasAccessTo.remove(access);
        }
        return false;
    }

    public Set<Access> getHasAccessTo() {
        return hasAccessTo;
    }

    public void setHasAccessTo(Set<Access> hasAccessTo) {
        this.hasAccessTo = hasAccessTo;
    }
}
