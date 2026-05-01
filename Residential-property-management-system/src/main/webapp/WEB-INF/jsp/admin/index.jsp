<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%
  User currentUser = (User) session.getAttribute("currentUser");
  if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>管理员首页 - 小区物业管理系统</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f5f5f5; }
    .navbar { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .navbar h1 { font-size: 20px; }
    .nav-links a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; transition: background 0.3s; }
    .nav-links a:hover { background: rgba(255,255,255,0.2); }
    .container { max-width: 1200px; margin: 30px auto; padding: 20px; }
    .dashboard { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; }
    .card { background: white; padding: 25px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    .card h3 { color: #333; margin-bottom: 15px; }
    .card p { color: #666; font-size: 14px; margin-bottom: 15px; }
    .card a { display: inline-block; padding: 8px 20px; background: #667eea; color: white; text-decoration: none; border-radius: 5px; }
    .welcome { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 30px; }
  </style>
</head>
<body>
<div class="navbar">
  <h1>👨‍💼 管理员控制台</h1>
  <div class="nav-links">
    <span>欢迎，<%= currentUser.getRealName() %></span>
    <a href="<%= request.getContextPath() %>/servlet/AdminServlet?action=userList&role=RESIDENT">住户管理</a>
    <a href="<%= request.getContextPath() %>/servlet/AdminServlet?action=userList&role=STAFF">员工管理</a>
    <a href="<%= request.getContextPath() %>/servlet/AdminServlet?action=userList&role=ADVERTISER">广告商管理</a>
    <a href="<%= request.getContextPath() %>/servlet/LoginServlet?action=logout">退出登录</a>
  </div>
</div>

<div class="container">
  <div class="welcome">
    <h2>欢迎回来，<%= currentUser.getRealName() %>！</h2>
    <p>当前在线用户数：<%= application.getAttribute("onlineUserCount") != null ? application.getAttribute("onlineUserCount") : 0 %></p>
  </div>

  <div class="dashboard">
    <div class="card">
      <h3>🏠 住户管理</h3>
      <p>管理小区住户信息、审核住户资料</p>
      <a href="<%= request.getContextPath() %>/servlet/AdminServlet?action=userList&role=RESIDENT">进入管理</a>
    </div>
    <div class="card">
      <h3>🔧 工作人员管理</h3>
      <p>管理物业工作人员信息、分配权限</p>
      <a href="<%= request.getContextPath() %>/servlet/AdminServlet?action=userList&role=STAFF">进入管理</a>
    </div>
    <div class="card">
      <h3>💰 费用管理</h3>
      <p>费用核算、缴费记录查看</p>
      <a href="#">进入管理</a>
    </div>
    <div class="card">
      <h3>📋 维修管理</h3>
      <p>报修审核、任务分配、进度管理</p>
      <a href="#">进入管理</a>
    </div>
    <div class="card">
      <h3>📢 广告管理</h3>
      <p>广告入驻申请审核、审批/驳回</p>
      <a href="#">进入管理</a>
    </div>
    <div class="card">
      <h3>📰 通知公告</h3>
      <p>发布小区通知、公告管理</p>
      <a href="#">进入管理</a>
    </div>
  </div>
</div>
</body>
</html>
