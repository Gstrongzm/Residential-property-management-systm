package com.property.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * 适配 MySQL 5.5.28，使用原生 JDBC 连接
 */
public class DBUtil {
    
    // 数据库配置（严格遵循要求）
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/property_management?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    static {
        try {
            // 加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("数据库驱动加载失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 获取数据库连接
     * @return Connection 数据库连接对象
     * @throws SQLException SQL异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    /**
     * 关闭资源
     * @param conn 连接
     * @param stmt 语句（可为null）
     * @param rs 结果集（可为null）
     */
    public static void close(Connection conn, java.sql.Statement stmt, java.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 关闭资源和 PreparedStatement
     * @param conn 连接
     * @param pstmt 预编译语句
     * @param rs 结果集
     */
    public static void close(Connection conn, java.sql.PreparedStatement pstmt, java.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
