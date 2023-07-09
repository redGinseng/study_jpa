package jpa.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpa.start.entity.Member;
import jpa.start.entity.Team;

public class JpaLab {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String... args) {
        emf = Persistence.createEntityManagerFactory("jpabook");
        em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        JpaLab jpaLab = new JpaLab();
//        jpaLab.testSave();

        updateRelation();

        tx.commit();

        em.close();

        emf.close();
    }


    public void testSave() {

        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);


    }


    private static void updateRelation() {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

}
