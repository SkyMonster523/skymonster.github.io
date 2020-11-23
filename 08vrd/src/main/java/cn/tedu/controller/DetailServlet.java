package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao pdao = new ProductDao();

        HttpSession session = request.getSession();
        String viewId = (String) session.getAttribute("view"+id);
        if (viewId==null){
            //让浏览器+1
            pdao.viewById(id);
            session.setAttribute("view"+id,id);
        }

        Product product = pdao.findById(id);
        Context context = new Context();
        context.setVariable("product",product);

        CategoryDao cDao = new CategoryDao();
        context.setVariable("list",cDao.findall());
        //添加浏览最多
        context.setVariable("vList",pdao.findViewList());
        //添加最后欢迎
        context.setVariable("lList",pdao.findLikeList());
        //得到登录的用户对象
        User user = (User)request.getSession().getAttribute("user");
        context.setVariable("user",user);
        ThUtils.print("detail.html",context,response);

    }
}
