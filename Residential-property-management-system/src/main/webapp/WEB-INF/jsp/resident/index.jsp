<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null || !"RESIDENT".equals(currentUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>住户首页 - 小区物业管理系统</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f5f5f5; }
        .navbar { background: linear-gradient(135deg, #28a745 0%, #20c997 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
        .navbar h1 { font-size: 20px; }
        .nav-links a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; transition: background 0.3s; }
        .nav-links a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 1200px; margin: 30px auto; padding: 20px; }
        .dashboard { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; }
        .card { background: white; padding: 25px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .card h3 { color: #333; margin-bottom: 15px; }
        .card p { color: #666; font-size: 14px; margin-bottom: 15px; }
        .card a { display: inline-block; padding: 8px 20px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; }
        .welcome { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 30px; }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>🏠 住户服务中心</h1>
        <div class="nav-links">
            <span>欢迎，<%= currentUser.getRealName() %></span>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=profile">个人信息</a>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=repair">在线报修</a>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=fee">费用查询</a>
            <a href="<%= request.getContextPath() %>/servlet/LoginServlet?action=logout">退出登录</a>
        </div>
    </div>
    
    <div class="container">
        <div class="welcome">
            <h2>欢迎回家，<%= currentUser.getRealName() %>！</h2>
            <p>如有问题请及时联系物业服务中心</p>
        </div>
        
        <div class="dashboard">
            <div class="card">
                <h3>👤 个人信息</h3>
                <p>查看和修改个人基本信息</p>
                <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=profile">查看详情</a>
            </div>
            <div class="card">
                <h3>🔧 在线报修</h3>
                <p>提交维修申请、查看进度</p>
                <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=repair">提交报修</a>
            </div>
            <div class="card">
                <h3>💰 费用查询</h3>
                <p>查看缴费记录、账单详情</p>
                <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=fee">查看账单</a>
            </div>
            <div class="card">
                <h3>📰 通知公告</h3>
                <p>查看小区最新通知</p>
                <a href="#">查看详情</a>
            </div>
        </div>
    </div>
</body>
</html>
