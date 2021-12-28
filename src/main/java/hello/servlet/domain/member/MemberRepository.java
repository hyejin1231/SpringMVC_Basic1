package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    // 동시성 문제가 고려되지 않았기 때문에 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야한다.
    // static이라 딱 하나만 생성된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // 싱글톤으로 만들 예정
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 값들을 가져와서 새로운 arraylist를 만들어서 뿌려줌 (그 안의 값을 건들고 싶지 않아서 ) store 자체 보호 위해
    }

    public void clearStore() {
        store.clear();

    }

}
