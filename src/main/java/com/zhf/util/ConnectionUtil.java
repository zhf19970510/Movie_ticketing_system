package com.zhf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


//所有工具类   功能方法都作为静态方法  方便调用
public class ConnectionUtil {
    private static String DRIVER_NAME = "";
    private static String URL = "";
    private static String USER_NAME = "";
    private static String PASSWORD = "";

    //给连接四要素赋予数据信息
    static {
        InputStream ins = null;
        Properties pp = null;
        try {
            ins = new FileInputStream("config/jdbc.properties");
            pp = new Properties();
            pp.load(ins);
            DRIVER_NAME = pp.getProperty("jdbc.driver");
            URL = pp.getProperty("jdbc.url");
            USER_NAME = pp.getProperty("jdbc.user");
            PASSWORD = pp.getProperty("jdbc.password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //1.获取连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //所有的查询工具方法
    public static void DQL(String sql, Object[] objs, DoResultSetValue doRS) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            //设置占位符
            for (int i = 0; i < objs.length; i++) {

                pst.setObject(i + 1, objs[i]);
            }
            //获得结果集
            rs = pst.executeQuery();
            //处理结果集 不同的查询语句，字段不一样
            if (rs != null) {
                doRS.doResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2.封装DML  (删除，修改，插入  3个操作称之为 DML操作)
    public static int DML(String sql, Object[] objs) {//objs用来保存传进来的参数 替换占位符的
        Connection conn = null;
        PreparedStatement pst = null;
        int rows = -1;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            //设置占位符号
            for (int i = 0; i < objs.length; i++) {
                //多态的写法
                pst.setObject((i + 1), objs[i]);
            }
            rows = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pst, null);
        }
        return rows;
    }

    public static boolean transaction(String[] sqls, Object[] objects) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean flag = true;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            for (String sql : sqls) {
                pstmt = conn.prepareStatement(sql);
                for (int i = 0; i < objects.length; i++) {
                    pstmt.setObject((i + 1), objects[i]);
                }
                pstmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                flag = false;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            close(conn, pstmt, null);
        }
        return flag;
    }

    //3.关闭资源的功能方法
    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
