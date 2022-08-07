package com.hello.core.discount;

import com.hello.core.AppConfig;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 할인율 정책에 대한 테스트코드
 * @Auth chanhyeok
 */
class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.getDiscountPolicy();
    }

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vipO(){
        //given
        Member memberVip = new Member(3L, "hyeok", Grade.VIP);
        //when
        int acutal = discountPolicy.discount(memberVip, 10000);
        //then
        assertThat(acutal).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vipx(){
        //given
        Member noneVipMember = new Member(1L, "no-money-customer", Grade.BASIC);
        //when
        int actual = discountPolicy.discount(noneVipMember, 10000);
        //then
        assertThat(actual).isEqualTo(0);
    }
}