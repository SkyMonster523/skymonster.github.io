package cn.tedu.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ThUtils {
	private static TemplateEngine te;
	static {
		//创建模板引擎对象
		te = new TemplateEngine();
		//创建解析器  该解析器会自动查找src/main/resources目录下的模板页面
		ClassLoaderTemplateResolver r = 
				new ClassLoaderTemplateResolver();
		//设置字符集
		r.setCharacterEncoding("UTF-8");
		//让解析器和模板引擎关联
		te.setTemplateResolver(r);
	}
	//Context导包 org.thymeleaf.Context
	public static void print(String fileName,Context context,
			HttpServletResponse response) throws IOException {
		//将页面和数据整合到一起的到一个新的html字符串
		String html = te.process(fileName, context);
		//把得到的新的html返回给浏览器   异常抛出
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(html);
		pw.close();
	}
}




