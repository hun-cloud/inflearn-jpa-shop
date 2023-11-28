import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");

            Member member2 = new Member();
            member2.setUsername("member2");

            Member member3 = new Member();
            member3.setUsername("member3");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            int count = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println(findMember);

            System.out.println(member1.getAge());
            System.out.println(count);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
