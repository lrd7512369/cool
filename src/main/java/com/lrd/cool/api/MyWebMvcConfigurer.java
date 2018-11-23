package com.lrd.cool.api;

import com.lrd.cool.api.entity.User;
import com.lrd.cool.api.utils.LogMan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lierdong
 * @date 2018-09-05
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer, HandlerInterceptor {

    private static final String URL_INDEX = "/index.html";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this)
                .addPathPatterns("/**.html");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        switch (url) {
            case URL_INDEX:
                HttpSession session = request.getSession();
                Object o = session.getAttribute("user");
                if (o == null) {
                    return true;
                }
                if (o instanceof User) {
                    User user = (User) o;
                    LogMan.i(user);
                    response.sendRedirect("/main.html");
                }
                break;
            default:
                break;
        }
        return true;
    }
}
