package com.property.dao;

import com.property.entity.User;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问层
 */
public class UserDAO {
    
    /**
     * 根据用户名和密码查询用户
     */
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND status = 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToUser(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据ID查询用户
     */
    public User findById(Integer userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToUser(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据用户名查询用户
     */
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToUser(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有用户（可按角色过滤）
     */
    public List<User> findAll(String role) throws SQLException {
        String sql;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (role != null && !role.isEmpty()) {
            sql = "SELECT * FROM users WHERE role = ? ORDER BY create_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role);
        } else {
            sql = "SELECT * FROM users ORDER BY create_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
        }
        
        rs = pstmt.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultSetToUser(rs));
        }
        return list;
    }
    
    /**
     * 插入用户
     */
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, real_name, phone, email, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRealName());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getRole());
            pstmt.setInt(7, user.getStatus() != null ? user.getStatus() : 1);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新用户
     */
    public int update(User user) throws SQLException {
        String sql = "UPDATE users SET real_name = ?, phone = ?, email = ?, status = ? WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getStatus());
            pstmt.setInt(5, user.getUserId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新最后登录时间
     */
    public int updateLastLoginTime(Integer userId) throws SQLException {
        String sql = "UPDATE users SET last_login_time = NOW() WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除用户
     */
    public int delete(Integer userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转User对象
     */
    private User resultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRealName(rs.getString("real_name"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getInt("status"));
        user.setCreateTime(rs.getTimestamp("create_time"));
        user.setLastLoginTime(rs.getTimestamp("last_login_time"));
        return user;
    }
}
