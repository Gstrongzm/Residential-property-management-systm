<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/1
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%@ page import="com.property.entity.Advertisement" %>
<%@ page import="java.util.List" %>
<%
  // 1. 权限验证
  User currentUser = (User) session.getAttribute("currentUser");
  if (currentUser == null || !"ADVERTISER".equals(currentUser.getRole())) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }

  // 2. 获取数据 (从 request 域中获取 Servlet 传来的列表)
  List<Advertisement> applyList = (List<Advertisement>) request.getAttribute("applyList");
  String message = (String) request.getAttribute("message");
  if (message == null) message = "";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>审核状态 - 小区物业管理系统</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: 'Microsoft YaHei', Arial, sans-serif; background: #f5f5f5; }

    .navbar {
      background: linear-gradient(135deg, #ffc107 0%, #e0a800 100%);
      color: white; padding: 15px 30px;
      display: flex; justify-content: space-between; align-items: center;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    .navbar h1 { font-size: 20px; }
    .nav-links a {
      color: white; text-decoration: none; margin-left: 20px;
      padding: 8px 15px; border-radius: 5px; transition: background 0.3s;
    }
    .nav-links a:hover { background: rgba(255,255,255,0.2); }

    .container { max-width: 1200px; margin: 30px auto; padding: 20px; }

    .status-card {
      background: white; padding: 30px; border-radius: 10px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    table {
      width: 100%; border-collapse: collapse; margin-top: 20px;
    }
    th, td {
      padding: 15px; text-align: left; border-bottom: 1px solid #eee;
    }
    th { background: #f8f9fa; color: #333; font-weight: 600; }
    tr:hover { background: #f1f1f1; }

    .badge {
      padding: 5px 10px; border-radius: 4px; font-size: 12px; font-weight: bold;
    }
    .badge-pending { background: #fff3cd; color: #856404; }
    .badge-approved { background: #d4edda; color: #155724; }
    .badge-rejected { background: #f8d7da; color: #721c24; }

    .empty-tip {
      text-align: center; padding: 40px; color: #999;
    }
    .alert {
      padding: 15px; background: #d4edda; color: #155724;
      border-radius: 5px; margin-bottom: 20px;
    }
    .btn-back {
      display: inline-block; margin-top: 20px;
      padding: 10px 20px; background: #6c757d; color: white;
      text-decoration: none; border-radius: 5px;
    }
  </style>
</head>
<body>

<div class="navbar">
  <h1>📢 广告商管理平台</h1>
  <div class="nav-links">
    <span>欢迎，<%= currentUser.getRealName() != null ? currentUser.getRealName() : currentUser.getUsername() %></span>
    <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">申请入驻</a>
    <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">审核状态</a>
    <a href="<%= request.getContextPath() %>/servlet/LoginServlet?action=logout">退出登录</a>
  </div>
</div>

<div class="container">
  <div class="status-card">
    <h2>📊 我的申请记录</h2>

    <% if (message != null && !message.isEmpty()) { %>
    <div class="alert"><%= message %></div>
    <% } %>

    <%
      if (applyList == null || applyList.isEmpty()) {
    %>
    <div class="empty-tip">
      <p>暂无申请记录</p>
      <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">去申请</a>
    </div>
    <%
    } else {
    %>
    <table>
      <thead>
      <tr>
        <th>申请编号</th>
        <th>公司名称</th>
        <th>广告类型</th>
        <th>申请时间</th>
        <th>审核状态</th>
        <th>审核意见</th>
      </tr>
      </thead>
      <tbody>
      <%
        for (Advertisement ad : applyList) {
          String adId = String.valueOf(ad.getAdId());
          String companyName = ad.getCompanyName() != null ? ad.getCompanyName() : "未填写";
          String adType = ad.getAdType() != null ? ad.getAdType() : "未指定";
          String applyTime = ad.getApplyTime() != null ? ad.getApplyTime().toString() : "未知";

          String status = ad.getStatus();
          String statusBadge = "badge-pending";
          String statusText = "审核中";

          if ("APPROVED".equals(status)) {
            statusBadge = "badge-approved";
            statusText = "已通过";
          } else if ("REJECTED".equals(status)) {
            statusBadge = "badge-rejected";
            statusText = "已驳回";
          }

          String reviewComment = ad.getReviewComment() != null ? ad.getReviewComment() : "-";
      %>
      <tr>
        <td><%= adId %></td>
        <td><%= companyName %></td>
        <td><%= adType %></td>
        <td><%= applyTime %></td>
        <td><span class="badge <%= statusBadge %>"><%= statusText %></span></td>
        <td><%= reviewComment %></td>
      </tr>
      <% } %>
      </tbody>
    </table>
    <% } %>

    <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=index" class="btn-back">返回首页</a>
  </div>
</div>

</body>
</html>
