package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseviewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseviewV2(Model model) {
        model.addAttribute("data", "hello!");

        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return "response/hello"; // @Controller에서 String 반환시 view의 논리적 이름이 되어버린다.
    }

    @RequestMapping("/response/hello") //권장하진 않는 방법 경로의 이름과 viewname이 같은 경우
    public void responseviewV3(Model model) {
        model.addAttribute("data", "hello!");}
        // 리턴 별도로 안해도 알아서 스프링이 처리해준다. "response/hello"라는게 논리적 view로
}
