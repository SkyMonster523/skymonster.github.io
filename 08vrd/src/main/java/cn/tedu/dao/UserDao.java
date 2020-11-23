package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User login(String username, String password) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql="select id from vrduser where username=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){//查询到登陆成功
                return new User(rs.getInt(1),username,password);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return  null;
    }

    public boolean check(String username) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "select id from vrduser where username=? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }

    public void insert(String username, String password) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "insert into vrduser values(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
            System.out.println("注册完成!");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
