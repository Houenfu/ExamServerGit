package com.itszt.ExamServer.interceptor;

import com.itszt.ExamServer.controller.UserController;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uuidSet = UserController.uuidSet.get("uuid");
        String uuid = request.getSession().getAttribute("uuid").toString();

        if(uuid.equals(uuidSet)){
            return true;
        }else{
            return false;
        }
    }
}
