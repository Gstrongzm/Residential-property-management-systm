package com.property.dao;

import com.property.entity.RepairRequest;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 报修申请数据访问层
 */
public class RepairRequestDAO {
    
    /**
     * 根据ID查询报修申请
     */
    public RepairRequest findById(Integer requestId) throws SQLException {
        String sql = "SELECT * FROM repair_requests WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, requestId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToRepairRequest(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据住户ID查询报修申请列表
     */
    public List<RepairRequest> findByResidentId(Integer residentId) throws SQLException {
        String sql = "SELECT * FROM repair_requests WHERE resident_id = ? ORDER BY request_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, residentId);
            rs = pstmt.executeQuery();
            
            List<RepairRequest> list = new ArrayList<>();
            while (rs.next()) {
                list.add(resultSetToRepairRequest(rs));
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据工作人员ID查询分配的报修任务
     */
    public List<RepairRequest> findByStaffId(Integer staffId) throws SQLException {
        String sql = "SELECT * FROM repair_requests WHERE assigned_staff_id = ? ORDER BY request_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            
            List<RepairRequest> list = new ArrayList<>();
            while (rs.next()) {
                list.add(resultSetToRepairRequest(rs));
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有报修申请（可按状态过滤）
     */
    public List<RepairRequest> findAll(String status) throws SQLException {
        String sql;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (status != null && !status.isEmpty()) {
            sql = "SELECT r.*, res.building_no, res.unit_no, res.room_no, u.real_name as resident_name " +
                  "FROM repair_requests r " +
                  "LEFT JOIN residents res ON r.resident_id = res.resident_id " +
                  "LEFT JOIN users u ON res.user_id = u.user_id " +
                  "WHERE r.status = ? ORDER BY r.request_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
        } else {
            sql = "SELECT r.*, res.building_no, res.unit_no, res.room_no, u.real_name as resident_name " +
                  "FROM repair_requests r " +
                  "LEFT JOIN residents res ON r.resident_id = res.resident_id " +
                  "LEFT JOIN users u ON res.user_id = u.user_id " +
                  "ORDER BY r.request_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
        }
        
        rs = pstmt.executeQuery();
        List<RepairRequest> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultSetToRepairRequest(rs));
        }
        return list;
    }
    
    /**
     * 插入报修申请
     */
    public int insert(RepairRequest request) throws SQLException {
        String sql = "INSERT INTO repair_requests (resident_id, request_title, request_desc, status) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, request.getResidentId());
            pstmt.setString(2, request.getRequestTitle());
            pstmt.setString(3, request.getRequestDesc());
            pstmt.setString(4, request.getStatus() != null ? request.getStatus() : "PENDING");
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    request.setRequestId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新报修申请（分配任务、处理进度等）
     */
    public int update(RepairRequest request) throws SQLException {
        String sql = "UPDATE repair_requests SET status = ?, assigned_staff_id = ?, handle_desc = ?, handle_time = ?, complete_time = ? WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, request.getStatus());
            pstmt.setInt(2, request.getAssignedStaffId());
            pstmt.setString(3, request.getHandleDesc());
            pstmt.setTimestamp(4, request.getHandleTime() != null ? new Timestamp(request.getHandleTime().getTime()) : null);
            pstmt.setTimestamp(5, request.getCompleteTime() != null ? new Timestamp(request.getCompleteTime().getTime()) : null);
            pstmt.setInt(6, request.getRequestId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除报修申请
     */
    public int delete(Integer requestId) throws SQLException {
        String sql = "DELETE FROM repair_requests WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, requestId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转RepairRequest对象
     */
    private RepairRequest resultSetToRepairRequest(ResultSet rs) throws SQLException {
        RepairRequest request = new RepairRequest();
        request.setRequestId(rs.getInt("request_id"));
        request.setResidentId(rs.getInt("resident_id"));
        request.setRequestTitle(rs.getString("request_title"));
        request.setRequestDesc(rs.getString("request_desc"));
        request.setRequestTime(rs.getTimestamp("request_time"));
        request.setStatus(rs.getString("status"));
        request.setAssignedStaffId(rs.getObject("assigned_staff_id") != null ? rs.getInt("assigned_staff_id") : null);
        request.setHandleDesc(rs.getString("handle_desc"));
        request.setHandleTime(rs.getTimestamp("handle_time"));
        request.setCompleteTime(rs.getTimestamp("complete_time"));
        return request;
    }
}
