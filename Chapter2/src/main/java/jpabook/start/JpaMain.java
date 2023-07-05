package jpabook.start;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            emf.close();
        }
    }

    public static void logic(EntityManager em) {
        String id = "id";
        Member member = new Member();
        member.setId(id);
        member.setUsername("상원");
        member.setAge(33);

        em.persist(member);

        member.setAge(20);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        List<Member> memberList =
            em.createQuery("select m from Member  m", Member.class).getResultList();
        System.out.println("memberList size :"+memberList.size());

        em.remove(member);
    }
}
