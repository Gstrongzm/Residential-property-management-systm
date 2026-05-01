package com.property.entity;

import java.util.Date;

/**
 * 物业工作人员实体类
 * 对应数据库表：staff
 */
public class Staff {
    private Integer staffId;
    private Integer userId;
    private String department;
    private String position;
    private Date hireDate;
    private Double salary;
    private Integer status; // 1-在职，0-离职
    
    // 关联用户信息
    private User user;

    public Staff() {}

    // Getters and Setters
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", userId=" + userId +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", status=" + status +
                '}';
    }
}
