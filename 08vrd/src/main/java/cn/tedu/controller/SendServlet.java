package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取参数
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        String categoryId = request.getParameter("categoryId");
        System.out.println(title);
        System.out.println(author);
        System.out.println(intro);
        System.out.println(categoryId);
        //接受上传的文件
        Part part = request.getPart("file");
        //上传文件的描述信息
        String info = part.getHeader("content-disposition");
        //获取后缀名
        String type = info.substring(info.lastIndexOf("."),info.length()-1);
        //唯一的文件名
        String fileName = UUID.randomUUID()+type;
        //得到Tomcat管辖范围的文件夹路径
        String path = request.getServletContext().getRealPath("images/");
        //得到日期相关路径
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd/");
        //当前时间的日期对象
        Date date = new Date();
        String timeStr = f.format(date);
        System.out.println(timeStr);
        path = path+timeStr;
        System.out.println(path);
        //创建文件夹
        new File(path).mkdirs();
        //把文件保存
        part.write(path+fileName);
        Product product = new Product(0,title,author,intro,
                "images/"+timeStr+fileName,0,0,
                System.currentTimeMillis(),Integer.parseInt(categoryId));
        ProductDao dao = new ProductDao();
        dao.insert(product);
        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
