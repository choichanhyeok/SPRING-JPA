package com.hello.core.beanfind;


import com.hello.core.AppConfig;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * 빈 조회를 위한 기본적인 3가지 방법과, 없는 스프링 빈 이름으로 조회시 발생하는 실패 테스트 까지
 * @Author chanhyeoKing
 */


public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService bean = ac.getBean("memberService", MemberService.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }
    
    @Test
    @DisplayName("구체 타입으로 조회")
    void findByDetailType(){
        MemberService bean = ac.getBean(MemberServiceImpl.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }
    
    //실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
