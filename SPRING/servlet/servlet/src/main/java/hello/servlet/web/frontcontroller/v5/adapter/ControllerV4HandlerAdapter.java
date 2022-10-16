package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    /**
     * 해당 핸들러가 ControllerV4가 맞는지 확인
     * @param handler
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // (1) 넘어온 핸들러를 ControllerV4로 변환
        ControllerV4 controller = (ControllerV4) handler;

        // (2) request로 부터 파라미터의 키 값과 value를 추출
        Map<String, String> paramMap = createParamMap(request);
        // (3) 4번의 controller.process에 넘겨 view로 넘겨주고 싶은 값을 저장할 HashMap model
        HashMap<String, Object> model = new HashMap<>();

        // (4) ControllerV4의 process 메서드를 통해 ViewName 추출,
        // 이 과정에서 model에 컨트롤러에서 view로 넘기고 싶은 값이 들어간다.
        String viewName = controller.process(paramMap, model);

        // (5) 해당 viewName의 ModelView 생성
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
