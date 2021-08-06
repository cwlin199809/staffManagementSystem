package com.cwlin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    //注意：这里不能用@ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Model model, HttpSession session){
        //具体业务：判断登录用户信息
        if(("cwlin".equals(username) || "Cwlin".equals(username)) && !StringUtils.isEmpty(password)){
            //登录成功
            //return "dashboard";
            session.setAttribute("loginUser",username);
            return "redirect:/main.html"; //重定向到main.html页面
        } else{
            //登录失败
            model.addAttribute("msg"," 用户名或者密码错误！");
            return "index"; //跳转到登陆页面
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session, Model model){
        //注销session
        session.invalidate();
        model.addAttribute("msg",null);
        return "redirect:/index.html";
    }
}
