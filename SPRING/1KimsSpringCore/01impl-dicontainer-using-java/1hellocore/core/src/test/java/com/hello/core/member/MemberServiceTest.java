package com.hello.core.member;



import com.hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.hello.core.member.Grade.*;

/**
 * 멤버에 관련된 동작에 대한 테스트 코드
 * @Auth chanhyeok
 */
public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        //given
        Member member = new Member(1L, "hyeok", VIP);
        //when
        memberService.join(member);
        //then
        Member expected = member;
        Member Exist = memberService.findMember(1L);
        Assertions.assertEquals(expected, Exist);
    }
}
