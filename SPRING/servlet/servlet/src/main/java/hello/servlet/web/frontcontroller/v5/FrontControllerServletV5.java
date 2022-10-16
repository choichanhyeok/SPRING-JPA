package hello.servlet.web.frontcontroller.v5;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. getHandler를 통해 http 요청에 맞는 핸들러를 호출
        Object handler = getHandler(req);

        // 1-2 HTTP request가 넘어온 url에 매핑되는 핸들러가 없을 경우
        if (handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. 핸들러를 넘겨서 관련 어뎁터 추출
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3. 어뎁터를 통한 핸들링
        ModelView mv = adapter.handle(req, resp, handler);

        // 4. ModelView로부터 viewName 추출 이후 ViewResolver를 통해 물리적 주소 도출
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);  // /front-controller/v5/v3/members/new-form
                                               // /WEB-INF/views/front-controller/v5/v3/members/new-form.jsp

        view.render(mv.getModel(), req, resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("can't find handler adapter. handler is " + handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
