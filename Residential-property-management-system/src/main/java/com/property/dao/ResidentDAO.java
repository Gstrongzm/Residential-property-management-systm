package com.property.dao;

import com.property.entity.Resident;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 住户数据访问层
 */
public class ResidentDAO {
    
    /**
     * 根据用户ID查询住户信息
     */
    public Resident findByUserId(Integer userId) throws SQLException {
        String sql = "SELECT * FROM residents WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToResident(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据ID查询住户信息
     */
    public Resident findById(Integer residentId) throws SQLException {
        String sql = "SELECT * FROM residents WHERE resident_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, residentId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToResident(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有住户信息
     */
    public List<Resident> findAll() throws SQLException {
        String sql = "SELECT r.*, u.username, u.real_name, u.phone FROM residents r " +
                     "LEFT JOIN users u ON r.user_id = u.user_id ORDER BY r.resident_id DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            List<Resident> list = new ArrayList<>();
            while (rs.next()) {
                Resident resident = resultSetToResident(rs);
                list.add(resident);
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 插入住户信息
     */
    public int insert(Resident resident) throws SQLException {
        String sql = "INSERT INTO residents (user_id, building_no, unit_no, room_no, area, owner_type, id_card, family_count, move_in_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, resident.getUserId());
            pstmt.setString(2, resident.getBuildingNo());
            pstmt.setString(3, resident.getUnitNo());
            pstmt.setString(4, resident.getRoomNo());
            pstmt.setDouble(5, resident.getArea() != null ? resident.getArea() : 0);
            pstmt.setString(6, resident.getOwnerType() != null ? resident.getOwnerType() : "OWNER");
            pstmt.setString(7, resident.getIdCard());
            pstmt.setInt(8, resident.getFamilyCount() != null ? resident.getFamilyCount() : 1);
            pstmt.setDate(9, resident.getMoveInDate() != null ? new java.sql.Date(resident.getMoveInDate().getTime()) : null);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    resident.setResidentId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新住户信息
     */
    public int update(Resident resident) throws SQLException {
        String sql = "UPDATE residents SET building_no = ?, unit_no = ?, room_no = ?, area = ?, owner_type = ?, id_card = ?, family_count = ?, move_in_date = ? WHERE resident_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, resident.getBuildingNo());
            pstmt.setString(2, resident.getUnitNo());
            pstmt.setString(3, resident.getRoomNo());
            pstmt.setDouble(4, resident.getArea());
            pstmt.setString(5, resident.getOwnerType());
            pstmt.setString(6, resident.getIdCard());
            pstmt.setInt(7, resident.getFamilyCount());
            pstmt.setDate(8, resident.getMoveInDate() != null ? new java.sql.Date(resident.getMoveInDate().getTime()) : null);
            pstmt.setInt(9, resident.getResidentId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除住户信息
     */
    public int delete(Integer residentId) throws SQLException {
        String sql = "DELETE FROM residents WHERE resident_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, residentId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转Resident对象
     */
    private Resident resultSetToResident(ResultSet rs) throws SQLException {
        Resident resident = new Resident();
        resident.setResidentId(rs.getInt("resident_id"));
        resident.setUserId(rs.getInt("user_id"));
        resident.setBuildingNo(rs.getString("building_no"));
        resident.setUnitNo(rs.getString("unit_no"));
        resident.setRoomNo(rs.getString("room_no"));
        resident.setArea(rs.getDouble("area"));
        resident.setOwnerType(rs.getString("owner_type"));
        resident.setIdCard(rs.getString("id_card"));
        resident.setFamilyCount(rs.getInt("family_count"));
        resident.setMoveInDate(rs.getDate("move_in_date"));
        return resident;
    }
}
