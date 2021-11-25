package kr.co.ppm.system.admin;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)
            throws Exception {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null) {
            Admin isLogin = (Admin) httpSession.getAttribute("login");
            if (isLogin != null) {
                return true;
            }
        }

        httpServletResponse.sendRedirect("/login");
        return false;
    }
}
