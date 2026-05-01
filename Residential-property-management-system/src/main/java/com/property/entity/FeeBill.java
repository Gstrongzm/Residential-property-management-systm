package com.property.entity;

import java.util.Date;

/**
 * 费用账单实体类
 * 对应数据库表：fee_bills
 */
public class FeeBill {
    private Integer billId;
    private Integer residentId;
    private Integer feeTypeId;
    private Double amount;
    private String period;
    private Date dueDate;
    private String status; // UNPAID, PAID, OVERDUE
    private Date payTime;
    private Date createTime;
    
    // 关联信息
    private Resident resident;
    private FeeType feeType;

    public FeeBill() {}

    // Getters and Setters
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    @Override
    public String toString() {
        return "FeeBill{" +
                "billId=" + billId +
                ", residentId=" + residentId +
                ", feeTypeId=" + feeTypeId +
                ", amount=" + amount +
                ", period='" + period + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
