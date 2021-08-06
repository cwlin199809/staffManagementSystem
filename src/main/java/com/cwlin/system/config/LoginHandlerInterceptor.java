package com.cwlin.system.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后应该有用户的session，session放在LoginController中
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null){ //没有登录
            request.setAttribute("msg","没有权限，请重新登录"); //显示信息
            request.getRequestDispatcher("/index.html").forward(request,response); //返回登录页面
            return false; //拦截
        }else {
            return true; //放行
        }
    }
}
