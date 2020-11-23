package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddBannerServlet",urlPatterns = "/addbanner")
public class AddBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String info = part.getHeader("content-disposition");
        //得到文件后缀名
        String type = info.substring(info.lastIndexOf("."),info.length()-1);
        String fileName = UUID.randomUUID()+type;//得到唯一文件名
        //得到保存文件名
        String path = request.getServletContext().getRealPath("/images/");
        part.write(path+fileName);//保存文件
        Banner banner = new Banner(0,"images/"+fileName);
        BannerDao dao = new BannerDao();
        dao.insert(banner);
        response.sendRedirect("/showbanner");//重定向到修改轮播图页面
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
