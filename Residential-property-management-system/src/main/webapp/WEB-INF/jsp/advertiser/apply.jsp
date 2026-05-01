<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2026/5/2
  Time: 00:34
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
    <title>申请入驻 - 广告商平台</title>
    <style>
        body { font-family: 'Microsoft YaHei', sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h2 { color: #333; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #666; }
        input, textarea, select { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
        button { width: 100%; padding: 12px; background: #ffc107; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }
        button:hover { background: #e0a800; }
        .back-link { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <h2>📝 广告入驻申请</h2>
    <form action="<%= request.getContextPath() %>/servlet/AdvertiserServlet" method="post">
        <input type="hidden" name="action" value="submitApply">
        <div class="form-group">
            <label>企业名称</label>
            <input type="text" name="companyName" required placeholder="请输入企业全称">
        </div>
        <div class="form-group">
            <label>联系人</label>
            <input type="text" name="contactPerson" value="<%= currentUser.getRealName() %>" readonly>
        </div>
        <div class="form-group">
            <label>联系电话</label>
            <input type="text" name="phone" required placeholder="请输入联系电话">
        </div>
        <div class="form-group">
            <label>广告类型</label>
            <select name="adType">
                <option value="电梯广告">电梯广告</option>
                <option value="户外大屏">户外大屏</option>
                <option value="道闸广告">道闸广告</option>
                <option value="社区横幅">社区横幅</option>
            </select>
        </div>
        <div class="form-group">
            <label>申请理由</label>
            <textarea name="description" rows="4" placeholder="请简述广告投放计划"></textarea>
        </div>
        <button type="submit">提交申请</button>
        <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=index" class="back-link">返回首页</a>
    </form>
</div>
</body>
</html>
