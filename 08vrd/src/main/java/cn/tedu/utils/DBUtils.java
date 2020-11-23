package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/*
* 数据库工具类中做的事儿:
* 1. 读取配置文件
* 2. 初始化连接池对象
* 3. 从连接池对象中获取连接
* */
public class DBUtils {

    private static DruidDataSource dds;
    static {
        Properties p = new Properties();
        InputStream ips =
                DBUtils.class.getClassLoader()
                        .getResourceAsStream("jdbc.properties");
        try {
            p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = p.getProperty("db.driver");
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");

        dds = new DruidDataSource();
        dds.setDriverClassName(driver);
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        //从配置文件中读取最大连接数量和初始连接数量
        String maxActive = p.getProperty("db.maxActive");
        String initialSize = p.getProperty("db.initialSize");
        dds.setInitialSize(Integer.parseInt(initialSize));
        dds.setMaxActive(Integer.parseInt(maxActive));
    }

    public static Connection getConn() throws Exception {
        return dds.getConnection();
    }
}
