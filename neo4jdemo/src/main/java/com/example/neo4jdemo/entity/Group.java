package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnoreProperties("belongto")
    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    public Set<Member> belongto;

    @JsonIgnoreProperties("hasAccessTo")
    @Relationship(type = "HAS_ACCESS", direction = Relationship.OUTGOING)
    public List<Resource> hasAccessTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getBelongto() {
        return belongto;
    }

    public void setBelongto(Set<Member> belongto) {
        this.belongto = belongto;
    }

    public List<Resource> getHasAccessTo() {
        return hasAccessTo;
    }

    public void addResource(Resource resource) {
        if (this.hasAccessTo == null) {
            this.hasAccessTo = new ArrayList<>();
        }
        this.hasAccessTo.add(resource);
    }
    public void setHasAccessTo(List<Resource> hasAccessTo) {
        this.hasAccessTo = hasAccessTo;
    }
}
