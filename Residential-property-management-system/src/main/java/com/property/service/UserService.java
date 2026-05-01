package com.property.service;

import com.property.dao.UserDAO;
import com.property.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户业务逻辑层
 */
public class UserService {
    
    private UserDAO userDAO = new UserDAO();
    
    /**
     * 用户登录
     */
    public User login(String username, String password) {
        try {
            return userDAO.login(username, password);
        } catch (SQLException e) {
            throw new RuntimeException("登录失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据ID查询用户
     */
    public User findById(Integer userId) {
        try {
            return userDAO.findById(userId);
        } catch (SQLException e) {
            throw new RuntimeException("查询用户失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据用户名查询用户
     */
    public User findByUsername(String username) {
        try {
            return userDAO.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException("查询用户失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有用户（可按角色过滤）
     */
    public List<User> findAll(String role) {
        try {
            return userDAO.findAll(role);
        } catch (SQLException e) {
            throw new RuntimeException("查询用户列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 注册用户
     */
    public int register(User user) {
        try {
            // 检查用户名是否已存在
            User existUser = userDAO.findByUsername(user.getUsername());
            if (existUser != null) {
                throw new RuntimeException("用户名已存在");
            }
            return userDAO.insert(user);
        } catch (SQLException e) {
            throw new RuntimeException("注册失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 更新用户信息
     */
    public int update(User user) {
        try {
            return userDAO.update(user);
        } catch (SQLException e) {
            throw new RuntimeException("更新用户失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 更新最后登录时间
     */
    public void updateLastLoginTime(Integer userId) {
        try {
            userDAO.updateLastLoginTime(userId);
        } catch (SQLException e) {
            // 不抛出异常，仅记录日志
            e.printStackTrace();
        }
    }
    
    /**
     * 删除用户
     */
    public int delete(Integer userId) {
        try {
            return userDAO.delete(userId);
        } catch (SQLException e) {
            throw new RuntimeException("删除用户失败：" + e.getMessage(), e);
        }
    }
}
