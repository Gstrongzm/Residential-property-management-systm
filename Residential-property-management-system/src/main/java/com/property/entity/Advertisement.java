package com.property.entity;

import java.util.Date;

/**
 * 广告申请实体类
 * 对应数据库表：advertisement_applications
 */
public class Advertisement {
    private Integer adId;
    private Integer userId;
    private String companyName;
    private String contactPerson;
    private String contactPhone;
    private String adType;
    private String adLocation;
    private Integer adDuration;
    private String adContent;
    private Date applyTime;
    private String status; // PENDING, APPROVED, REJECTED
    private Integer reviewAdminId;
    private Date reviewTime;
    private String reviewComment;
    
    // 关联信息
    private User user;
    private User reviewAdmin;

    public Advertisement() {}

    // Getters and Setters
    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdLocation() {
        return adLocation;
    }

    public void setAdLocation(String adLocation) {
        this.adLocation = adLocation;
    }

    public Integer getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(Integer adDuration) {
        this.adDuration = adDuration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReviewAdminId() {
        return reviewAdminId;
    }

    public void setReviewAdminId(Integer reviewAdminId) {
        this.reviewAdminId = reviewAdminId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getReviewAdmin() {
        return reviewAdmin;
    }

    public void setReviewAdmin(User reviewAdmin) {
        this.reviewAdmin = reviewAdmin;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "adId=" + adId +
                ", companyName='" + companyName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
