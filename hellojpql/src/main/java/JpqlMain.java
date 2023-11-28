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

            Team team1 = new Team();
            team1.setName("팀A");
            Team team2 = new Team();
            team2.setName("팀B");
            Team team3 = new Team();
            team3.setName("팀C");

            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(team2);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();
            System.out.println(resultList.size());

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
