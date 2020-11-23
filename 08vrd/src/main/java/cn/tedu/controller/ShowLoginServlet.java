package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet",urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过模板引擎将resources目录下的页面返回给浏览器
        Context context = new Context();
        Cookie[] cookies = request.getCookies();
        //非空判断 避免空指针异常
        if(cookies!=null){
            for (Cookie c:cookies){
                //取出cookies的name和value
                String name = c.getName();
                String value = c.getValue();
                if(name.equals("username")){
                    context.setVariable("username",value);
                }
                if(name.equals("password")){
                    context.setVariable("password",value);
                }
            }
        }
        ThUtils.print("login.html",context,response);
    }
}
