package com.property.entity;

import java.util.Date;

/**
 * 费用类型实体类
 * 对应数据库表：fee_types
 */
public class FeeType {
    private Integer feeTypeId;
    private String feeName;
    private Double feeUnitPrice;
    private String feeUnit;
    private String description;

    public FeeType() {}

    // Getters and Setters
    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public Double getFeeUnitPrice() {
        return feeUnitPrice;
    }

    public void setFeeUnitPrice(Double feeUnitPrice) {
        this.feeUnitPrice = feeUnitPrice;
    }

    public String getFeeUnit() {
        return feeUnit;
    }

    public void setFeeUnit(String feeUnit) {
        this.feeUnit = feeUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FeeType{" +
                "feeTypeId=" + feeTypeId +
                ", feeName='" + feeName + '\'' +
                ", feeUnitPrice=" + feeUnitPrice +
                ", feeUnit='" + feeUnit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
