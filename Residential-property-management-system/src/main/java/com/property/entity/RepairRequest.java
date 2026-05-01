package com.property.entity;

import java.util.Date;

/**
 * 报修申请实体类
 * 对应数据库表：repair_requests
 */
public class RepairRequest {
    private Integer requestId;
    private Integer residentId;
    private String requestTitle;
    private String requestDesc;
    private Date requestTime;
    private String status; // PENDING, ASSIGNED, IN_PROGRESS, COMPLETED, REJECTED
    private Integer assignedStaffId;
    private String handleDesc;
    private Date handleTime;
    private Date completeTime;
    
    // 关联信息
    private Resident resident;
    private Staff staff;

    public RepairRequest() {}

    // Getters and Setters
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAssignedStaffId() {
        return assignedStaffId;
    }

    public void setAssignedStaffId(Integer assignedStaffId) {
        this.assignedStaffId = assignedStaffId;
    }

    public String getHandleDesc() {
        return handleDesc;
    }

    public void setHandleDesc(String handleDesc) {
        this.handleDesc = handleDesc;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "RepairRequest{" +
                "requestId=" + requestId +
                ", requestTitle='" + requestTitle + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
