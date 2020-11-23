package cn.tedu.dao;

import cn.tedu.entity.Banner;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BannerDao {
    public List<Banner> findAll() {
        ArrayList<Banner> list = new ArrayList<>();
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "select * from banner";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String url = rs.getString(2);
                list.add(new Banner(id,url));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return list;
    }

    public Banner findById(String id) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "select * from banner where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String url = rs.getString(2);
                return new Banner(Integer.parseInt(id),url);
            }
            System.out.println("查找完成");
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    public void deleteById(String id) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "delete from banner where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
            System.out.println("删除完成");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public void insert(Banner banner) {
        //获取数据库连接
        try(Connection conn = DBUtils.getConn();) {
            String sql = "insert into banner values(null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,banner.getUrl());
            ps.executeUpdate();
            System.out.println("保存完成!");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
