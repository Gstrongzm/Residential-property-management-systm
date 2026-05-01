<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
  // 从session获取数据，方便后面使用
  com.property.entity.User currentUser = (com.property.entity.User) session.getAttribute("currentUser");
  com.property.entity.Resident residentInfo = (com.property.entity.Resident) session.getAttribute("residentInfo");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>个人信息 - 小区物业管理系统</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Microsoft YaHei', Arial, sans-serif;
      background: #f5f5f5;
    }

    .navbar {
      background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
      padding: 15px 30px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    .navbar h1 {
      color: white;
      font-size: 20px;
    }

    .nav-links {
      display: flex;
      gap: 1.5rem;
      align-items: center;
    }

    .nav-links a {
      color: white;
      text-decoration: none;
      padding: 8px 15px;
      border-radius: 5px;
      transition: background 0.3s;
    }

    .nav-links a:hover {
      background: rgba(255,255,255,0.2);
    }

    .container {
      max-width: 800px;
      margin: 30px auto;
      padding: 20px;
    }

    .content-card {
      background: white;
      border-radius: 10px;
      padding: 30px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    .content-card h2 {
      color: #28a745;
      margin-bottom: 25px;
      text-align: center;
      font-size: 24px;
    }

    .profile-info {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 20px;
    }

    .info-item {
      padding: 15px;
      background: #f9f9f9;
      border-radius: 8px;
      border-left: 4px solid #28a745;
    }

    .info-item label {
      display: block;
      color: #666;
      font-size: 14px;
      margin-bottom: 8px;
    }

    .info-item span {
      display: block;
      color: #333;
      font-size: 16px;
      font-weight: bold;
    }

    .btn-back {
      display: inline-block;
      margin-top: 25px;
      padding: 12px 30px;
      background: #6c757d;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      text-align: center;
      transition: background 0.3s;
    }

    .btn-back:hover {
      background: #5a6268;
    }
  </style>
</head>
<body>
<div class="navbar">
  <h1>🏠 个人信息</h1>
  <div class="nav-links">
    <span style="color: white;">欢迎，<%= currentUser != null ? currentUser.getRealName() : "" %></span>
    <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=index">首页</a>
    <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=repair">报修申请</a>
    <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=fee">缴费查询</a>
    <a href="<%= request.getContextPath() %>/servlet/LoginServlet?action=logout">退出登录</a>
  </div>
</div>

<div class="container">
  <div class="content-card">
    <h2>个人信息详情</h2>

    <div class="profile-info">
      <div class="info-item">
        <label>用户名</label>
        <span><%= currentUser != null ? currentUser.getUsername() : "-" %></span>
      </div>
      <div class="info-item">
        <label>姓名</label>
        <span><%= currentUser != null && currentUser.getRealName() != null ? currentUser.getRealName() : "-" %></span>
      </div>
      <div class="info-item">
        <label>联系电话</label>
        <span><%= currentUser != null && currentUser.getPhone() != null ? currentUser.getPhone() : "-" %></span>
      </div>
      <div class="info-item">
        <label>电子邮箱</label>
        <span><%= currentUser != null && currentUser.getEmail() != null ? currentUser.getEmail() : "-" %></span>
      </div>
      <div class="info-item">
        <label>房号</label>
        <span>
          <%= residentInfo != null ?
                  residentInfo.getBuildingNo() + "栋 " +
                          residentInfo.getUnitNo() + "单元 " +
                          residentInfo.getRoomNo() + "室" : "-" %>
        </span>
      </div>
      <div class="info-item">
        <label>面积</label>
        <span><%= residentInfo != null && residentInfo.getArea() != null ? residentInfo.getArea() + " ㎡" : "-" %></span>
      </div>
      <div class="info-item">
        <label>业主类型</label>
        <span>
          <%= residentInfo != null ?
                  ("OWNER".equals(residentInfo.getOwnerType()) ? "业主" : "租户") : "-" %>
        </span>
      </div>
      <div class="info-item">
        <label>身份证号</label>
        <span><%= residentInfo != null && residentInfo.getIdCard() != null ? residentInfo.getIdCard() : "-" %></span>
      </div>
      <div class="info-item">
        <label>家庭人数</label>
        <span><%= residentInfo != null && residentInfo.getFamilyCount() != null ? residentInfo.getFamilyCount() + " 人" : "-" %></span>
      </div>
      <div class="info-item">
        <label>入住日期</label>
        <span><%= residentInfo != null && residentInfo.getMoveInDate() != null ? residentInfo.getMoveInDate() : "-" %></span>
      </div>
      <div class="info-item">
        <label>账户状态</label>
        <span style="color: <%= currentUser != null && currentUser.getStatus() == 1 ? "#28a745" : "#dc3545" %>;">
          <%= currentUser != null ? (currentUser.getStatus() == 1 ? "正常" : "禁用") : "未知" %>
        </span>
      </div>
      <div class="info-item">
        <label>注册时间</label>
        <span><%= currentUser != null && currentUser.getCreateTime() != null ? currentUser.getCreateTime() : "-" %></span>
      </div>
    </div>

    <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=index" class="btn-back">返回首页</a>
  </div>
</div>
</body>
</html>
