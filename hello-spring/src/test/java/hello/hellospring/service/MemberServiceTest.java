package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    // 레포지토리를 비우기 위해 레포지토리 객체를 생성한다.
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    // 해당 테스트 파일에서 사용되는 repository와 MemberService 파일에서 사용되는 repository와 서로 다름
    // 현재는 MemoryMemberRepository에 'static'이 붙어 있어 문제가 없지만 이론적으로 같은 repository로 테스트 해야함
    // 해결 방법 : MemberService 파일 변경 후 @BeforeEach 애너테이션 사용
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach  // 각 테스트를 실행하기 전에 행하는 메서드
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // 생성자 파라미터를 입력해주면서 같은 리포지토리를 사용할 수 있도록 함  = DI !!
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test // 정상 플로우 테스트
    void 회원가입() {
        //given -> 무언가 주어졌음
        Member member = new Member();
        member.setName("hello");

        //when -> 주어진 무언가를 실행했을 때
        Long saveId = memberService.join(member);

        //then -> 결과가 이게 나와야 함
        Member findmember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    // 정상 플로우 테스트도 중요하지만 예외가 발생했을 때의 플로우 테스트도 매우 중요
    @Test
    public void 중복_회원_예외() { //테스트는 메서드 이름이 한글이어도 문제가 발생하지 않는다.
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //try-catch문을 대신해서 assertThrows 메서드를 사용한다. 파라미터로 (발생할 예외, 실행할 코드)

//        memberService.join(member1);
//        try {
//            memberService.join(member2); // 이름이 같기 때문에 join메서드에서 중복회원검증에서 걸림
//            fail();
//        } catch (IllegalStateException e) {
//
//        }

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}