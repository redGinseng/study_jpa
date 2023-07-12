package jpa.start.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    // 연관관계 추가. 반대편에서는 ManyToOne
    // mappedBy는 왜 필요할까?
    // 객체에는 양방향 연관관계라는게 없다. 서로 다른 단방향 연관관계 2개를 묶어서 양방향인 것처럼 보이게 할 뿐.
    // 테이블에서는 외래키가 하나 뿐인데, 객체이서는 연관관계가 2개인 차이를 해결하기 위해,
    // 두 연관관계중 하나에 외래키 관리 주체를 설정한다
    // 외래키 관리 주체는 데이터베이스 연관관계와 매핑되고, 외래키를 관리(CRUD) 할 수 있다.
    // 주체가 아닌쪽은 Read 권한만 갖는다.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

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

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }


    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {

    }


}