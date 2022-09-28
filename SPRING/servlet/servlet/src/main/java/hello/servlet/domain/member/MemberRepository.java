package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야함.
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 톰캣 띄울때만 스프링 쓸거고, 지금은 스프링 안쓸거라 그냥 싱글톤 객체 직접 만들어서 내려줌
    // 클래스 로딩이 끝나면 메서드 영역에서 바로 끌어다 쓸 수 있게 static으로 선언
    final static MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }
    
    // 객체를 넘겨주지 않고 values를 잡아서 넘겨줌 (store 자체를 보호하기 위해서, store 내부의 객체에 접근해 수정하면 수정은 됨)
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

