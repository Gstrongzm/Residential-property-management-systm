package com.property.dao;

import com.property.entity.Advertisement;
import com.property.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 广告申请数据访问层
 */
public class AdvertisementDAO {
    
    /**
     * 根据ID查询广告申请
     */
    public Advertisement findById(Integer adId) throws SQLException {
        String sql = "SELECT * FROM advertisement_applications WHERE ad_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, adId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return resultSetToAdvertisement(rs);
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 根据用户ID查询广告申请列表
     */
    public List<Advertisement> findByUserId(Integer userId) throws SQLException {
        String sql = "SELECT * FROM advertisement_applications WHERE user_id = ? ORDER BY apply_time DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            
            List<Advertisement> list = new ArrayList<>();
            while (rs.next()) {
                list.add(resultSetToAdvertisement(rs));
            }
            return list;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    /**
     * 查询所有广告申请（可按状态过滤）
     */
    public List<Advertisement> findAll(String status) throws SQLException {
        String sql;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (status != null && !status.isEmpty()) {
            sql = "SELECT * FROM advertisement_applications WHERE status = ? ORDER BY apply_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
        } else {
            sql = "SELECT * FROM advertisement_applications ORDER BY apply_time DESC";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
        }
        
        rs = pstmt.executeQuery();
        List<Advertisement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(resultSetToAdvertisement(rs));
        }
        return list;
    }
    
    /**
     * 插入广告申请
     */
    public int insert(Advertisement ad) throws SQLException {
        String sql = "INSERT INTO advertisement_applications (user_id, company_name, contact_person, contact_phone, ad_type, ad_location, ad_duration, ad_content, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ad.getUserId());
            pstmt.setString(2, ad.getCompanyName());
            pstmt.setString(3, ad.getContactPerson());
            pstmt.setString(4, ad.getContactPhone());
            pstmt.setString(5, ad.getAdType());
            pstmt.setString(6, ad.getAdLocation());
            pstmt.setInt(7, ad.getAdDuration() != null ? ad.getAdDuration() : 0);
            pstmt.setString(8, ad.getAdContent());
            pstmt.setString(9, ad.getStatus() != null ? ad.getStatus() : "PENDING");
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    ad.setAdId(rs.getInt(1));
                }
            }
            return rows;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 更新广告申请（审核）
     */
    public int update(Advertisement ad) throws SQLException {
        String sql = "UPDATE advertisement_applications SET status = ?, review_admin_id = ?, review_time = ?, review_comment = ? WHERE ad_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ad.getStatus());
            pstmt.setInt(2, ad.getReviewAdminId());
            pstmt.setTimestamp(3, ad.getReviewTime() != null ? new Timestamp(ad.getReviewTime().getTime()) : null);
            pstmt.setString(4, ad.getReviewComment());
            pstmt.setInt(5, ad.getAdId());
            
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * 删除广告申请
     */
    public int delete(Integer adId) throws SQLException {
        String sql = "DELETE FROM advertisement_applications WHERE ad_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, adId);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    
    /**
     * ResultSet转Advertisement对象
     */
    private Advertisement resultSetToAdvertisement(ResultSet rs) throws SQLException {
        Advertisement ad = new Advertisement();
        ad.setAdId(rs.getInt("ad_id"));
        ad.setUserId(rs.getInt("user_id"));
        ad.setCompanyName(rs.getString("company_name"));
        ad.setContactPerson(rs.getString("contact_person"));
        ad.setContactPhone(rs.getString("contact_phone"));
        ad.setAdType(rs.getString("ad_type"));
        ad.setAdLocation(rs.getString("ad_location"));
        ad.setAdDuration(rs.getInt("ad_duration"));
        ad.setAdContent(rs.getString("ad_content"));
        ad.setApplyTime(rs.getTimestamp("apply_time"));
        ad.setStatus(rs.getString("status"));
        ad.setReviewAdminId(rs.getObject("review_admin_id") != null ? rs.getInt("review_admin_id") : null);
        ad.setReviewTime(rs.getTimestamp("review_time"));
        ad.setReviewComment(rs.getString("review_comment"));
        return ad;
    }
}
