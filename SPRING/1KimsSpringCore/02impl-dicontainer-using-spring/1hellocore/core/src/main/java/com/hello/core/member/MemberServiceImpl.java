package com.hello.core.member;


/**
 *  AppConfig를 이용한 의존성 주입 테스트를 위해 작성한 memberServiceImpl
 *  MemberRepository를 주입받는다. MemberRepositry에 대한 구현체는 AppConfig에서 관리하기에
 *  SRP(단일 책임 원칙), DIP(의존관계 역전 원칙), OCP(개방 폐쇄 원칙)을 준수할 수 있게 되었다.
 * @Author chanHyeok
 */


public class MemberServiceImpl implements MemberService{
    //    private final MemberRepository memberRepository = new MemoryMemberRepository(); => 객체를 직접 생성했을 때 에시를 위해 만든 구문,
    //                                                                                       아래 생성자 주입으로 대체됌
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
