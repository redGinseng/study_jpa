package jpa.start.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}