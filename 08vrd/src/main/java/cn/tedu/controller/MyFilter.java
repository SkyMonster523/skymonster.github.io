package cn.tedu.controller;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsemd","/send","/showbanner","/delete","/deletebanner","/addbanner","*.jpg"})
public class MyFilter implements Filter {
    public void destroy() {
    }
    //在访问服务器资源之前会执行此方法
    //此方法中可以进行一些权限判断,控制是否允许访问
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){//没登录
            response.sendRedirect("/showlogin");
        }else{//登录了
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
