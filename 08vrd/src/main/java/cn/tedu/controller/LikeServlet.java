package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikeServlet",urlPatterns = "/like")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();
        String likeId = (String) session.getAttribute("like"+id);
        if(likeId==null){//没点赞
            ProductDao dao = new ProductDao();
            dao.likeById(id);
            //把点过赞的id保存到session
            session.setAttribute("like"+id,id);
        }
        //重定向回到详情页面
        response.sendRedirect("/detail?id="+id);
    }
}
