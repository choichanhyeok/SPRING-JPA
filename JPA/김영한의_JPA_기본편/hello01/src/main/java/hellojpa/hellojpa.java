package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * jpa 동작 원리를 보기 위한 연습용 코드
 * @author hyeoKing, based on KYH
 */
public class hellojpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //META-INFO에서 해당 persistenceUnit 찾어
        EntityManager em = emf.createEntityManager();       // 엔티티 매니저 팩토리로부터, 엔티티 매니저를 할당받음

        EntityTransaction tx = em.getTransaction();         // 엔티티 매니저로부터 트랜잭션 할당
        tx.begin();                                         // 트랜잭션 실행

        try {
//            Member member = new Member();                   // 멤버 받아서,
//            member.setId(2L);                               // INSERT INTO .. ~
//            member.setName("HelloCs");
//            em.persist(member);                             // 앤티티에 매니저에 지금 생성한 객체를 등록해줌

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");         // 멤버 객체를 찾아, 이름만 수정해줘도- 커밋시에 JPA가 이를 파악해 알아서 UPDATE 해줌

            // JPQL 예시
            List<Member> result = em.createQuery("select m from Member as m", Member.class) // 특이한 건 대상이 테이블이 아니라 객체
                    .setFirstResult(5) // 5번부터
                    .setMaxResults(11) // 11개 가져와
                    .getResultList();

            for (Member member: result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();                                    // 트랜잭션 커밋해주기
        } catch (Exception e){
            tx.rollback();                                  // 위 과정중 실패시 롤백
        } finally {
            em.close();                                     // 과정 종료시 무조건, 엔티티 매니저 할당 해제
        }
        emf.close();                                        // 엔티티 매니저 팩토리 할당 해제
    }
}
