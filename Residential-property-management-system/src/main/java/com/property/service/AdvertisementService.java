package com.property.service;

import com.property.dao.AdvertisementDAO;
import com.property.entity.Advertisement;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 广告申请业务逻辑层
 */
public class AdvertisementService {
    
    private AdvertisementDAO advertisementDAO = new AdvertisementDAO();
    
    /**
     * 根据ID查询广告申请
     */
    public Advertisement findById(Integer adId) {
        try {
            return advertisementDAO.findById(adId);
        } catch (SQLException e) {
            throw new RuntimeException("查询广告申请失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据用户ID查询广告申请列表
     */
    public List<Advertisement> findByUserId(Integer userId) {
        try {
            return advertisementDAO.findByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException("查询广告申请列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有广告申请（可按状态过滤）
     */
    public List<Advertisement> findAll(String status) {
        try {
            return advertisementDAO.findAll(status);
        } catch (SQLException e) {
            throw new RuntimeException("查询广告申请列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 提交广告申请
     */
    public int submit(Advertisement ad) {
        try {
            ad.setApplyTime(new Date());
            ad.setStatus("PENDING");
            return advertisementDAO.insert(ad);
        } catch (SQLException e) {
            throw new RuntimeException("提交广告申请失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 审核广告申请
     */
    public int review(Integer adId, String status, String comment, Integer adminId) {
        try {
            Advertisement ad = advertisementDAO.findById(adId);
            if (ad == null) {
                throw new RuntimeException("广告申请不存在");
            }
            ad.setStatus(status);
            ad.setReviewAdminId(adminId);
            ad.setReviewTime(new Date());
            ad.setReviewComment(comment);
            return advertisementDAO.update(ad);
        } catch (SQLException e) {
            throw new RuntimeException("审核广告申请失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 删除广告申请
     */
    public int delete(Integer adId) {
        try {
            return advertisementDAO.delete(adId);
        } catch (SQLException e) {
            throw new RuntimeException("删除广告申请失败：" + e.getMessage(), e);
        }
    }
}
