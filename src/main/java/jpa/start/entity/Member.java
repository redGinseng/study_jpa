package jpa.start.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT", joinColumns = @JoinColumn(name = "MEMBER_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> product = new ArrayList<Product>();

    public Member() {
    }

    public Member(final String id, final String username) {
        this.id = id;
        this.username = username;
    }

    // 연관관계 설정
    public void setTeam(final Team team) {
        this.team = team;

        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        team.setMembers(List.of(this));
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }
}