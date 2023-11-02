package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements  MemberRepository {

    // 실무에서는 공유되는 변수일 경우 동시성 문제로 ConcurrentHashMap 사용해야함
    private static Map<Long, Member> store = new HashMap<>();
//    실무에서는 동시성 문제로 AtomicLong
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        get()의 값이 없을 경우를 위해 Optional 사용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
//        멤버들을 모두 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
