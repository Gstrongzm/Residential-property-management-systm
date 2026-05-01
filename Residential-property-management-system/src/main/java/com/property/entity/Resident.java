package com.property.entity;

import java.util.Date;

/**
 * 住户信息实体类
 * 对应数据库表：residents
 */
public class Resident {
    private Integer residentId;
    private Integer userId;
    private String buildingNo;
    private String unitNo;
    private String roomNo;
    private Double area;
    private String ownerType; // OWNER, RENTER
    private String idCard;
    private Integer familyCount;
    private Date moveInDate;
    
    // 关联用户信息
    private User user;

    public Resident() {}

    // Getters and Setters
    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", userId=" + userId +
                ", buildingNo='" + buildingNo + '\'' +
                ", unitNo='" + unitNo + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", area=" + area +
                ", ownerType='" + ownerType + '\'' +
                '}';
    }
}
