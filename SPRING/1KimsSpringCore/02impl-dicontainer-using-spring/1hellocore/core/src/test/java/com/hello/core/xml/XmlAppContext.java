package com.hello.core.xml;

import com.hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * xml로 빈 등록후, GenericXmlApplicationContext로 빈을 가져오는 과정을 보기위한 테스트 코드
 */
public class XmlAppContext {
    @Test
    void xmlAppContext(){
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
