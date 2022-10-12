package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 테스트 파일은 다른 곳에 가져가 사용하지 않기 때문에 제어타입이 필요 없다
class MemoryMemberRepositoryTest {
    // 테스트할 클래스의 객체를 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트가 끝날 때마다 호출되는 메서드를 가리키는 애너테이션
    public void afterEach() {
        repository.clearStore();
    }

    // 테스트 할 메서드를 작성하고 @Test 애너테이션을 붙인 뒤 실행하면 테스트를 실행함
    // 내부 코드는 메인메서드 작성하는 것과 비슷
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // 반환 타입이 optional이기 때문에 뒤에 get() 메서드를 넣어줘야 함 -> 좋은 방법은 아님
        // 테스트 검증 -> member의 값과 result의 값이 같아야 함

        // 첫 번째 방법 (junit의 api를 가져와서 사용하는 방법)
        // Assertions.assertEquals(result, member);

        // 두 번째 방법 (assertj의 api를 가져와서 사용하는 방법)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift+f6 누르면 member 이름 한번에 바뀜
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
