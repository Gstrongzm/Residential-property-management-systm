package com.property.service;

import com.property.dao.ResidentDAO;
import com.property.entity.Resident;

import java.sql.SQLException;
import java.util.List;

/**
 * 住户业务逻辑层
 */
public class ResidentService {
    
    private ResidentDAO residentDAO = new ResidentDAO();
    
    /**
     * 根据用户ID查询住户信息
     */
    public Resident findByUserId(Integer userId) {
        try {
            return residentDAO.findByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException("查询住户信息失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据ID查询住户信息
     */
    public Resident findById(Integer residentId) {
        try {
            return residentDAO.findById(residentId);
        } catch (SQLException e) {
            throw new RuntimeException("查询住户信息失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有住户信息
     */
    public List<Resident> findAll() {
        try {
            return residentDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("查询住户列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 添加住户信息
     */
    public int add(Resident resident) {
        try {
            return residentDAO.insert(resident);
        } catch (SQLException e) {
            throw new RuntimeException("添加住户失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 更新住户信息
     */
    public int update(Resident resident) {
        try {
            return residentDAO.update(resident);
        } catch (SQLException e) {
            throw new RuntimeException("更新住户失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 删除住户信息
     */
    public int delete(Integer residentId) {
        try {
            return residentDAO.delete(residentId);
        } catch (SQLException e) {
            throw new RuntimeException("删除住户失败：" + e.getMessage(), e);
        }
    }
}
