<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      padding: 2rem;
    }

    .navbar {
      background: rgba(255, 255, 255, 0.95);
      padding: 1rem 2rem;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 2rem;
      border-radius: 10px;
    }

    .navbar h1 {
      color: #667eea;
      font-size: 1.5rem;
    }

    .nav-links {
      display: flex;
      gap: 1.5rem;
      align-items: center;
    }

    .nav-links a {
      text-decoration: none;
      color: #333;
      padding: 0.5rem 1rem;
      border-radius: 5px;
      transition: all 0.3s;
    }

    .nav-links a:hover {
      background: #667eea;
      color: white;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      background: white;
      border-radius: 15px;
      padding: 2rem;
      box-shadow: 0 10px 30px rgba(0,0,0,0.2);
    }

    .container h2 {
      color: #667eea;
      margin-bottom: 1.5rem;
      text-align: center;
    }

    .profile-info {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 1.5rem;
    }

    .info-item {
      padding: 1rem;
      background: #f9f9f9;
      border-radius: 8px;
      border-left: 4px solid #667eea;
    }

    .info-item label {
      display: block;
      color: #666;
      font-size: 0.9rem;
      margin-bottom: 0.5rem;
    }

    .info-item span {
      display: block;
      color: #333;
      font-size: 1.1rem;
      font-weight: bold;
    }

    .edit-btn {
      display: block;
      width: 100%;
      margin-top: 2rem;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      padding: 1rem;
      border: none;
      border-radius: 8px;
      font-size: 1.1rem;
      cursor: pointer;
      transition: transform 0.3s, box-shadow 0.3s;
      text-align: center;
      text-decoration: none;
    }

    .edit-btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
    }
  </style>
</head>
<body>
<div class="navbar">
  <h1>👤 个人信息</h1>
  <div class="nav-links">
    <a href="${pageContext.request.contextPath}/servlet/StaffServlet?action=index">首页</a>
    <a href="${pageContext.request.contextPath}/servlet/StaffServlet?action=tasks">我的任务</a>
    <a href="${pageContext.request.contextPath}/servlet/StaffServlet?action=logout">退出登录</a>
  </div>
</div>

<div class="container">
  <h2>个人信息详情</h2>

  <div class="profile-info">
    <div class="info-item">
      <label>用户名</label>
      <span>${sessionScope.currentUser.username}</span>
    </div>
    <div class="info-item">
      <label>姓名</label>
      <c:choose>
        <c:when test="${not empty sessionScope.staffInfo}">
          <span>${sessionScope.staffInfo.name}</span>
        </c:when>
        <c:otherwise>
          <span>-</span>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="info-item">
      <label>职位</label>
      <c:choose>
        <c:when test="${not empty sessionScope.staffInfo}">
          <span>${sessionScope.staffInfo.position}</span>
        </c:when>
        <c:otherwise>
          <span>-</span>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="info-item">
      <label>联系电话</label>
      <c:choose>
        <c:when test="${not empty sessionScope.staffInfo}">
          <span>${sessionScope.staffInfo.phone}</span>
        </c:when>
        <c:otherwise>
          <span>-</span>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="info-item">
      <label>入职时间</label>
      <c:choose>
        <c:when test="${not empty sessionScope.staffInfo}">
          <span>${sessionScope.staffInfo.hireDate}</span>
        </c:when>
        <c:otherwise>
          <span>-</span>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="info-item">
      <label>账户状态</label>
      <span>${sessionScope.currentUser.status == 'ACTIVE' ? '正常' : '禁用'}</span>
    </div>
  </div>

  <a href="#" class="edit-btn">编辑信息（待实现）</a>
</div>
</body>
</html>
