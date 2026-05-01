package com.property.service;

import com.property.dao.StaffDAO;
import com.property.entity.Staff;

import java.sql.SQLException;
import java.util.List;

/**
 * 工作人员业务逻辑层
 */
public class StaffService {
    
    private StaffDAO staffDAO = new StaffDAO();
    
    /**
     * 根据用户ID查询工作人员信息
     */
    public Staff findByUserId(Integer userId) {
        try {
            return staffDAO.findByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException("查询工作人员信息失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据ID查询工作人员信息
     */
    public Staff findById(Integer staffId) {
        try {
            return staffDAO.findById(staffId);
        } catch (SQLException e) {
            throw new RuntimeException("查询工作人员信息失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有工作人员信息
     */
    public List<Staff> findAll() {
        try {
            return staffDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("查询工作人员列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 添加工作人员信息
     */
    public int add(Staff staff) {
        try {
            return staffDAO.insert(staff);
        } catch (SQLException e) {
            throw new RuntimeException("添加工作人员失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 更新工作人员信息
     */
    public int update(Staff staff) {
        try {
            return staffDAO.update(staff);
        } catch (SQLException e) {
            throw new RuntimeException("更新工作人员失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 删除工作人员信息
     */
    public int delete(Integer staffId) {
        try {
            return staffDAO.delete(staffId);
        } catch (SQLException e) {
            throw new RuntimeException("删除工作人员失败：" + e.getMessage(), e);
        }
    }
}
