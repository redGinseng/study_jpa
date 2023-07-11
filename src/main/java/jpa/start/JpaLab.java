package jpa.start;

import java.util.List;
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
        jpaLab.testSaveNonOwner();

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

    public void testSaveNonOwner() {


        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team1 = new Team("team1", "팀1");
        team1.setMembers(List.of(member1, member2));
        em.persist(team1);

    }



    private static void updateRelation() {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void deleteRelation() {

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    public void biDirection() {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for (Member member : members) {

            System.out.println("member.username = " +
                member.getUsername());
        }

    }

    // SELECT M.* FROM MEMBER MEMBER
    // INNER JOIN
    //      TEAM TEAM ON MEMBER.TEAM_ID = TEAM1_.ID
    // WHERE
    // TEAM1_.ID =
    public static void queryLogicJoint(EntityManager em) {
        String jpql = "select m from Member m join m.team t where " +
            "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
            .setParameter("teamName", "팀1")
            .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" +
                member.getUsername());
        }


    }

}
