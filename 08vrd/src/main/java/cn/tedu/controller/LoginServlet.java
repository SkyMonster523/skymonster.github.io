package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");

        //获取传递过来的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名:"+username+"密码:"+password);
        //得到是否记住用户名和密码的变量
        String rem = request.getParameter("rem");
        System.out.println("是否记住用户名和密码:"+rem);

        UserDao dao = new UserDao();

        User user = dao.login(username,password);
        //判断是否登录成功
        if(user!=null){
            //通过session记住登录状态
            //得到session对象 第一次调用getsession内部会自动创建一个新的session对象
            //同时把sessionid通过cookie下发给浏览器
            //之后在调用getSession时会通过cookie中的sessionid去内存中找到id对应的session对象
            HttpSession session = request.getSession();
            //把登陆成功的用户对象保存进session中
            session.setAttribute("user",user);

            //判读那是否登陆成功
            if (rem!=null){
                //把用户名和密码装进cookie
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //下发给浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            response.sendRedirect("/home");
        }else{
            response.sendRedirect("/showlogin");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
