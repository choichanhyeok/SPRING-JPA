package com.hello.core;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.member.MemoryMemberRepository;
import com.hello.core.order.OrderService;
import com.hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * DIP (의존관계 역전의 원칙을 지키기 위해, 실제 동작에 필요한 구현 객체를 Appconfig에서 생성해줌.)
 * @author chanhyeok
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), getDiscountPolicy());
//        return null;
    }

//    @Bean //
    /**
     * 리턴되는 RateDiscountPolicy 통합 테스트에서 AutoConfig의 RateDiscountPolicy와 충돌 발생
     * 전반적인 테스트에는 문제 없으나, 싱글톤을 확인하기 위한 테스트 코드에서 올바른 값을 얻기 위해선 @Bean을 다시 등록해줘야함
     * @return 가상화되지 않은 새로 생성된 RateDiscountPolicy객체, 싱글톤을 보장하지 못함.
     */
    public DiscountPolicy getDiscountPolicy(){
        return new RateDiscountPolicy();
    }

    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
}


// 1. 어떤 클래스에서 인터페이스가 아닌 클래스 객체를 직접 생성하면 무조건 DIP(의존관계역전) 위반인가?
// 2. 인터페이스 분리 원칙은
// 3. 스프링에서 Impl 쓰는 이유,인터페이스를 @Service로 등록하라는건가? 그게 아니면 IoC를 통해 의존성 주입을 알아서 해주는데 그게 필요한가?