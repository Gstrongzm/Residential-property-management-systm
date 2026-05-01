package com.property.dao;

import com.property.entity.FeeBill;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 费用账单数据访问层
 */
public class FeeBillDAO {
    
    /**
     * 根据ID查询费用账单
     */
    public FeeBill findById(Integer billId) throws SQLException {
        String sql = "SELECT * FROM fee_bills WHERE bill_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, billId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToFeeBill(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据住户ID查询费用账单列表
     */
    public List<FeeBill> findByResidentId(Integer residentId) throws SQLException {
        String sql = "SELECT * FROM fee_bills WHERE resident_id = ? ORDER BY create_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, residentId);
            rs = pstmt.executeQuery();
            
            List<FeeBill> list = new ArrayList<>();
            while (rs.next()) {
                list.add(resultSetToFeeBill(rs));
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有费用账单（可按状态过滤）
     */
    public List<FeeBill> findAll(String status) throws SQLException {
        String sql;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (status != null && !status.isEmpty()) {
            sql = "SELECT f.*, r.building_no, r.unit_no, r.room_no, u.real_name as resident_name " +
                  "FROM fee_bills f " +
                  "LEFT JOIN residents r ON f.resident_id = r.resident_id " +
                  "LEFT JOIN users u ON r.user_id = u.user_id " +
                  "WHERE f.status = ? ORDER BY f.create_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
        } else {
            sql = "SELECT f.*, r.building_no, r.unit_no, r.room_no, u.real_name as resident_name " +
                  "FROM fee_bills f " +
                  "LEFT JOIN residents r ON f.resident_id = r.resident_id " +
                  "LEFT JOIN users u ON r.user_id = u.user_id " +
                  "ORDER BY f.create_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
        }
        
        rs = pstmt.executeQuery();
        List<FeeBill> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultSetToFeeBill(rs));
        }
        return list;
    }
    
    /**
     * 插入费用账单
     */
    public int insert(FeeBill bill) throws SQLException {
        String sql = "INSERT INTO fee_bills (resident_id, fee_type_id, amount, period, due_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, bill.getResidentId());
            pstmt.setInt(2, bill.getFeeTypeId());
            pstmt.setDouble(3, bill.getAmount());
            pstmt.setString(4, bill.getPeriod());
            pstmt.setDate(5, bill.getDueDate() != null ? new java.sql.Date(bill.getDueDate().getTime()) : null);
            pstmt.setString(6, bill.getStatus() != null ? bill.getStatus() : "UNPAID");
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    bill.setBillId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新费用账单（缴费、状态变更）
     */
    public int update(FeeBill bill) throws SQLException {
        String sql = "UPDATE fee_bills SET status = ?, pay_time = ? WHERE bill_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bill.getStatus());
            pstmt.setTimestamp(2, bill.getPayTime() != null ? new Timestamp(bill.getPayTime().getTime()) : null);
            pstmt.setInt(3, bill.getBillId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除费用账单
     */
    public int delete(Integer billId) throws SQLException {
        String sql = "DELETE FROM fee_bills WHERE bill_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, billId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转FeeBill对象
     */
    private FeeBill resultSetToFeeBill(ResultSet rs) throws SQLException {
        FeeBill bill = new FeeBill();
        bill.setBillId(rs.getInt("bill_id"));
        bill.setResidentId(rs.getInt("resident_id"));
        bill.setFeeTypeId(rs.getInt("fee_type_id"));
        bill.setAmount(rs.getDouble("amount"));
        bill.setPeriod(rs.getString("period"));
        bill.setDueDate(rs.getDate("due_date"));
        bill.setStatus(rs.getString("status"));
        bill.setPayTime(rs.getTimestamp("pay_time"));
        bill.setCreateTime(rs.getTimestamp("create_time"));
        return bill;
    }
}
