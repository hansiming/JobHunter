package com.cszjo.jobhunter.interceptor;

import com.cszjo.jobhunter.annotation.RequireLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * Created by Han on 2017/3/5.
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod controllerMethod = (HandlerMethod) handler;
        Annotation requireLogin = controllerMethod.getMethodAnnotation(RequireLogin.class);
        if (requireLogin != null && request.getSession().getAttribute("userInfo") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
