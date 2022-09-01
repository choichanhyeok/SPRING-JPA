package com.hello.core.order;

import com.hello.core.annotation.MainDiscountPolicy;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 *  AppConfig를 이용한 의존성 주입 테스트를 위해 작성한 OrderServiceImpl
 *  MemberRepository, DiscountPolicy를 주입받는다. 인터페이스에 대한 구현체는 AppConfig에서 관리하기에
 *  SRP(단일 책임 원칙), DIP(의존관계 역전 원칙), OCP(개방 폐쇄 원칙)을 준수할 수 있게 되었다.
 *
 *  + 별도로 단일 책임 원칙 준수를 위해, 할인에 관한 메서드를 별도로 분리해 rateDiscountPlicy에 정의했다.
 * @Author chanHyeok
 */

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일책임 원칙 충족

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
