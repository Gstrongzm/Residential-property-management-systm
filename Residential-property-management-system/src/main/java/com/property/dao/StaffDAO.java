package com.property.dao;

import com.property.entity.Staff;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作人员数据访问层
 */
public class StaffDAO {
    
    /**
     * 根据用户ID查询工作人员信息
     */
    public Staff findByUserId(Integer userId) throws SQLException {
        String sql = "SELECT * FROM staff WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToStaff(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据ID查询工作人员信息
     */
    public Staff findById(Integer staffId) throws SQLException {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToStaff(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有工作人员信息
     */
    public List<Staff> findAll() throws SQLException {
        String sql = "SELECT s.*, u.username, u.real_name, u.phone FROM staff s " +
                     "LEFT JOIN users u ON s.user_id = u.user_id ORDER BY s.staff_id DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            List<Staff> list = new ArrayList<>();
            while (rs.next()) {
                Staff staff = resultSetToStaff(rs);
                list.add(staff);
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 插入工作人员信息
     */
    public int insert(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (user_id, department, position, hire_date, salary, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, staff.getUserId());
            pstmt.setString(2, staff.getDepartment());
            pstmt.setString(3, staff.getPosition());
            pstmt.setDate(4, staff.getHireDate() != null ? new java.sql.Date(staff.getHireDate().getTime()) : null);
            pstmt.setDouble(5, staff.getSalary() != null ? staff.getSalary() : 0);
            pstmt.setInt(6, staff.getStatus() != null ? staff.getStatus() : 1);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    staff.setStaffId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新工作人员信息
     */
    public int update(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET department = ?, position = ?, hire_date = ?, salary = ?, status = ? WHERE staff_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staff.getDepartment());
            pstmt.setString(2, staff.getPosition());
            pstmt.setDate(3, staff.getHireDate() != null ? new java.sql.Date(staff.getHireDate().getTime()) : null);
            pstmt.setDouble(4, staff.getSalary());
            pstmt.setInt(5, staff.getStatus());
            pstmt.setInt(6, staff.getStaffId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除工作人员信息
     */
    public int delete(Integer staffId) throws SQLException {
        String sql = "DELETE FROM staff WHERE staff_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转Staff对象
     */
    private Staff resultSetToStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setStaffId(rs.getInt("staff_id"));
        staff.setUserId(rs.getInt("user_id"));
        staff.setDepartment(rs.getString("department"));
        staff.setPosition(rs.getString("position"));
        staff.setHireDate(rs.getDate("hire_date"));
        staff.setSalary(rs.getDouble("salary"));
        staff.setStatus(rs.getInt("status"));
        return staff;
    }
}
