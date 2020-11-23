package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
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
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到分类id
        String cid = request.getParameter("cid");

        //得到查询关键字
        String keyword = request.getParameter("keyword");

        //取出Session中保存的user对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*if(user!=null){
            System.out.println("曾经登录过");
        }else{
            System.out.println("没有登陆过");
        }*/


        Context context = new Context();
        context.setVariable("user",user);
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findall();
        context.setVariable("list",list);

        BannerDao bdao = new BannerDao();
        List<Banner> blist = bdao.findAll();
        context.setVariable("blist",blist);

        //创建ProductDao 并调用findAll方法
        List<Product> pList = null;
        ProductDao pDao = new ProductDao();
        if(cid!=null) {//查分类方法
            pList = pDao.findByCid(cid);
        }else if (keyword!=null){
            pList = pDao.findByKeyword(keyword);
        }else{//查所有
            pList = pDao.findall();
        }
        context.setVariable("pList",pList);


        //查询浏览最多的作品信息
        List<Product> vList = pDao.findViewList();
        context.setVariable("vList",vList);

        //查询最受欢迎的作品信息
        List<Product> lList = pDao.findLikeList();
        context.setVariable("lList",lList);

        ThUtils.print("home.html",context,response);
    }
}
