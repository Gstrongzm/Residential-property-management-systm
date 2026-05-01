<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null || !"ADVERTISER".equals(currentUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>广告商首页 - 小区物业管理系统</title>
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
            background: linear-gradient(135deg, #ffc107 0%, #e0a800 100%);
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .navbar h1 {
            font-size: 20px;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }

        .nav-links a:hover {
            background: rgba(255,255,255,0.2);
        }

        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 20px;
        }

        .welcome-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .welcome-card h2 {
            color: #333;
            margin-bottom: 15px;
        }

        .welcome-card p {
            color: #666;
            font-size: 14px;
        }

        .dashboard {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .card h3 {
            color: #333;
            margin-bottom: 15px;
        }

        .card p {
            color: #666;
            font-size: 14px;
            margin-bottom: 15px;
        }

        .card a {
            display: inline-block;
            padding: 8px 20px;
            background: linear-gradient(135deg, #ffc107 0%, #e0a800 100%);
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: transform 0.2s;
        }

        .card a:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>📢 广告商管理平台</h1>
    <div class="nav-links">
        <span>欢迎，<%= currentUser.getRealName() %></span>
        <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">申请入驻</a>
        <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">审核状态</a>
        <a href="<%= request.getContextPath() %>/servlet/LoginServlet?action=logout">退出登录</a>
    </div>
</div>

<div class="container">
    <div class="welcome-card">
        <h2>欢迎回来，<%= currentUser.getRealName() %>！</h2>
        <p>您可以在这里提交广告入驻申请，实时查看审核状态，管理您的广告投放。</p>
    </div>

    <div class="dashboard">
        <div class="card">
            <h3>📝 申请入驻</h3>
            <p>在线提交广告入驻申请，填写相关信息</p>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">立即申请</a>
        </div>

        <div class="card">
            <h3>📊 审核状态</h3>
            <p>实时查看申请审核进度和结果</p>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">查看状态</a>
        </div>
    </div>
</div>
</body>
</html>
