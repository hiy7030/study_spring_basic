package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키값을 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // return store.get(id); -> 만약 불러온 값이 null일 경우 발생
        return Optional.ofNullable(store.get(id)); // optional로 감싸서 null이 발생할 경우 방지
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 입력받은 이름과 같은 것이 있으면
                .findAny(); // 찾은 값을 반환
    }

    @Override
    public List<Member> findAll() { //반환타입은 List이고 입력된 값의 타입은 Map
        return new ArrayList<>(store.values());
    }

    // 테스트가 끝날 때마다 repository를 비워주는 메서드를 생성
    public void clearStore() {
        store.clear();
    }
}
