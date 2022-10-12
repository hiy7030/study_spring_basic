package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 회원 리포지토리 생성
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 테스트와 서로 다른 repository를 사용하기 때문에 이 문제를 해결하기 위한 방법
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // MemberRepository를 내가 new로 생성하는 것이 아니라 외부에서 파라미터를 받아 생성해 준다.


    //회원가입 메서드
    public Long join(Member member) {
        // 같은 이름을 가진 중복 회원은 X
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 중복 로직 메서드화 하는 법 -> ctrl+shift+alt+t -> Extra Method
    private void validateDuplicateMember(Member member) { // 중복회원검증 메서드
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 메서드
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
