package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // (1) 핸들러를 ControllerV3로 형변환
        ControllerV3 controller = (ControllerV3) handler;
        // (2) request의 파라미터의 key와 value를 저장할 Map
        Map<String, String> paramMap = createParamMap(request);
        // (3) controller.process로 paramMap를 넘겨 Model View 추출
        ModelView mv = controller.process(paramMap);   // ** V3의 경우 controller가 ViewName을 적용해 ModelView를 내려준다.
                                                       // V4의 경우 여기서 ModelView를 생성해서 컨트롤러로 주고, viewResolver도 여기서 호출
                                                       // 그래서 컨트롤러는 편해질 수 있음.
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
