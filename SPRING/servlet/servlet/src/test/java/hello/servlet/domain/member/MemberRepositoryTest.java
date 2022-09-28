package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest{
//    MemberRepository memberRepository = new MemberRepository();
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void find(){
        //given
        Member saveMember = new Member("hyeok", 26);
        saveMember.setId(1L);

        //when
        memberRepository.save(saveMember);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isSameAs(saveMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("hyeok", 26);
        Member member2 = new Member("gain", 26);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        Assertions.assertThat(result).contains(member1, member2);
    }

}


















//// Junit 5부터는 public 없어도 된다.
//class MemberRepositoryTest {
////    MemberRepository memberRepository = new MemberRepository(); // 이러면 안됨. 싱글톤이기 때문에
//    MemberRepository memberRepository = MemberRepository.getInstance();
//
//    @AfterEach
//    void afterEach(){
//        memberRepository.clearStore();
//    }
//
//    @Test
//    void save() {
//        //given
//        Member member = new Member("hyeok", 26);
//
//        //when
//        Member saveMember = memberRepository.save(member);
//
//        //then
//        Member findMember = memberRepository.findById(saveMember.getId());
//
//        Assertions.assertThat(findMember).isSameAs(saveMember);
//    }
//
//    @Test
//    void findAll(){
//
//        //given
//        Member member1 = new Member("hyeok", 26);
//        Member member2 = new Member("gain",26);
//
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//
//        //when
//        List<Member> result = memberRepository.findAll();
//
//        //then
//        Assertions.assertThat(result.size()).isEqualTo(2);
//        Assertions.assertThat(result).contains(member1, member2);
//    }
//}