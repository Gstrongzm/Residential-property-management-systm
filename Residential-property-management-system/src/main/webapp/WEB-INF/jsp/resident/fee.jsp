<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>缴费查询 - 小区物业管理系统</title>
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
      max-width: 1000px;
      margin: 0 auto;
      background: white;
      border-radius: 15px;
      padding: 2rem;
      box-shadow: 0 10px 30px rgba(0,0,0,0.2);
    }

    .container h2 {
      color: #667eea;
      margin-bottom: 1.5rem;
    }

    .fee-summary {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 1rem;
      margin-bottom: 2rem;
    }

    .summary-card {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      padding: 1.5rem;
      border-radius: 10px;
      text-align: center;
    }

    .summary-card h3 {
      font-size: 0.9rem;
      opacity: 0.9;
      margin-bottom: 0.5rem;
    }

    .summary-card p {
      font-size: 1.8rem;
      font-weight: bold;
    }

    .fee-table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 1rem;
    }

    .fee-table th,
    .fee-table td {
      padding: 1rem;
      text-align: left;
      border-bottom: 1px solid #e0e0e0;
    }

    .fee-table th {
      background: #f5f5f5;
      color: #333;
      font-weight: bold;
    }

    .fee-table tr:hover {
      background: #f9f9f9;
    }

    .status-badge {
      display: inline-block;
      padding: 0.25rem 0.75rem;
      border-radius: 20px;
      font-size: 0.85rem;
      font-weight: bold;
    }

    .status-paid {
      background: #d4edda;
      color: #155724;
    }

    .status-unpaid {
      background: #f8d7da;
      color: #721c24;
    }

    .status-overdue {
      background: #fff3cd;
      color: #856404;
    }

    .pay-btn {
      display: inline-block;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      padding: 0.5rem 1rem;
      border-radius: 5px;
      text-decoration: none;
      font-size: 0.9rem;
      transition: all 0.3s;
    }

    .pay-btn:hover {
      transform: scale(1.05);
      box-shadow: 0 3px 10px rgba(102, 126, 234, 0.4);
    }

    .empty-message {
      text-align: center;
      padding: 3rem;
      color: #666;
    }
  </style>
</head>
<body>
<div class="navbar">
  <h1>💰 缴费查询</h1>
  <div class="nav-links">
    <a href="${pageContext.request.contextPath}/servlet/ResidentServlet?action=index">首页</a>
    <a href="${pageContext.request.contextPath}/servlet/ResidentServlet?action=profile">个人信息</a>
    <a href="${pageContext.request.contextPath}/servlet/ResidentServlet?action=repair">报修申请</a>
    <a href="${pageContext.request.contextPath}/servlet/ResidentServlet?action=logout">退出登录</a>
  </div>
</div>

<div class="container">
  <h2>我的缴费记录</h2>

  <div class="fee-summary">
    <div class="summary-card">
      <h3>待缴费用</h3>
      <p>¥0.00</p>
    </div>
    <div class="summary-card">
      <h3>已缴费用</h3>
      <p>¥0.00</p>
    </div>
    <div class="summary-card">
      <h3>累计缴费</h3>
      <p>¥0.00</p>
    </div>
  </div>

  <table class="fee-table">
    <thead>
    <tr>
      <th>缴费单号</th>
      <th>费用类型</th>
      <th>金额</th>
      <th>缴费周期</th>
      <th>截止日期</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td colspan="7" class="empty-message">暂无缴费记录</td>
    </tr>
    </tbody>
  </table>
</div>
</body>
</html>
