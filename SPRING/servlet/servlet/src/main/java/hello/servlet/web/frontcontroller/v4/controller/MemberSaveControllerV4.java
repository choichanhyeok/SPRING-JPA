package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 이미 파싱되서 넘어온 파라미터 값을 그냥 적용만 하면 됨
        Member member = new Member(paramMap.get("username"), Integer.parseInt(paramMap.get("age")));
        memberRepository.save(member);

        // 모델 객체도 frontController에서 알아서 적용해줘서 그냥 put만 하고 요청 url로 받을 viewName만 리턴해주면 됨
        model.put("member", member);
        return "save-result";
    }
}
