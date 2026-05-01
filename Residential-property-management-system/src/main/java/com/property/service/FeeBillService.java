package com.property.service;

import com.property.dao.FeeBillDAO;
import com.property.entity.FeeBill;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 费用账单业务逻辑层
 */
public class FeeBillService {
    
    private FeeBillDAO feeBillDAO = new FeeBillDAO();
    
    /**
     * 根据ID查询费用账单
     */
    public FeeBill findById(Integer billId) {
        try {
            return feeBillDAO.findById(billId);
        } catch (SQLException e) {
            throw new RuntimeException("查询费用账单失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据住户ID查询费用账单列表
     */
    public List<FeeBill> findByResidentId(Integer residentId) {
        try {
            return feeBillDAO.findByResidentId(residentId);
        } catch (SQLException e) {
            throw new RuntimeException("查询费用账单列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有费用账单（可按状态过滤）
     */
    public List<FeeBill> findAll(String status) {
        try {
            return feeBillDAO.findAll(status);
        } catch (SQLException e) {
            throw new RuntimeException("查询费用账单列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 创建费用账单
     */
    public int create(FeeBill bill) {
        try {
            bill.setCreateTime(new Date());
            return feeBillDAO.insert(bill);
        } catch (SQLException e) {
            throw new RuntimeException("创建费用账单失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 缴费
     */
    public int pay(Integer billId) {
        try {
            FeeBill bill = feeBillDAO.findById(billId);
            if (bill == null) {
                throw new RuntimeException("费用账单不存在");
            }
            bill.setStatus("PAID");
            bill.setPayTime(new Date());
            return feeBillDAO.update(bill);
        } catch (SQLException e) {
            throw new RuntimeException("缴费失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 删除费用账单
     */
    public int delete(Integer billId) {
        try {
            return feeBillDAO.delete(billId);
        } catch (SQLException e) {
            throw new RuntimeException("删除费用账单失败：" + e.getMessage(), e);
        }
    }
}
