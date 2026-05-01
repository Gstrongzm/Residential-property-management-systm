package com.property.service;

import com.property.dao.RepairRequestDAO;
import com.property.entity.RepairRequest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 报修申请业务逻辑层
 */
public class RepairRequestService {
    
    private RepairRequestDAO repairRequestDAO = new RepairRequestDAO();
    
    /**
     * 根据ID查询报修申请
     */
    public RepairRequest findById(Integer requestId) {
        try {
            return repairRequestDAO.findById(requestId);
        } catch (SQLException e) {
            throw new RuntimeException("查询报修申请失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据住户ID查询报修申请列表
     */
    public List<RepairRequest> findByResidentId(Integer residentId) {
        try {
            return repairRequestDAO.findByResidentId(residentId);
        } catch (SQLException e) {
            throw new RuntimeException("查询报修申请列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 根据工作人员ID查询分配的报修任务
     */
    public List<RepairRequest> findByStaffId(Integer staffId) {
        try {
            return repairRequestDAO.findByStaffId(staffId);
        } catch (SQLException e) {
            throw new RuntimeException("查询报修任务失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 查询所有报修申请（可按状态过滤）
     */
    public List<RepairRequest> findAll(String status) {
        try {
            return repairRequestDAO.findAll(status);
        } catch (SQLException e) {
            throw new RuntimeException("查询报修申请列表失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 提交报修申请
     */
    public int submit(RepairRequest request) {
        try {
            request.setRequestTime(new Date());
            request.setStatus("PENDING");
            return repairRequestDAO.insert(request);
        } catch (SQLException e) {
            throw new RuntimeException("提交报修申请失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 分配维修任务
     */
    public int assignTask(Integer requestId, Integer staffId) {
        try {
            RepairRequest request = repairRequestDAO.findById(requestId);
            if (request == null) {
                throw new RuntimeException("报修申请不存在");
            }
            request.setAssignedStaffId(staffId);
            request.setStatus("ASSIGNED");
            return repairRequestDAO.update(request);
        } catch (SQLException e) {
            throw new RuntimeException("分配任务失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 更新维修进度
     */
    public int updateProgress(Integer requestId, String status, String handleDesc) {
        try {
            RepairRequest request = repairRequestDAO.findById(requestId);
            if (request == null) {
                throw new RuntimeException("报修申请不存在");
            }
            request.setStatus(status);
            request.setHandleDesc(handleDesc);
            request.setHandleTime(new Date());
            if ("COMPLETED".equals(status)) {
                request.setCompleteTime(new Date());
            }
            return repairRequestDAO.update(request);
        } catch (SQLException e) {
            throw new RuntimeException("更新进度失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 删除报修申请
     */
    public int delete(Integer requestId) {
        try {
            return repairRequestDAO.delete(requestId);
        } catch (SQLException e) {
            throw new RuntimeException("删除报修申请失败：" + e.getMessage(), e);
        }
    }
}
