package com.cwlin.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //viewResolver实现了视图解析器接口类，就可以把它看作是视图解析器
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    //静态内部类，视图解析器就需要实现ViewResolver接口
    private static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String s, Locale locale) {
            return null;
        }
    }

    //国际化解析器注册进组件
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置自定义拦截器，设置拦截哪些请求
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/css/**","/js/**","/img/**");
        //静态资源：*.css、*.js、*.img也不被拦截，为了登录失败时页面可以正常加载
    }

    //视图跳转，首页定制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
}
