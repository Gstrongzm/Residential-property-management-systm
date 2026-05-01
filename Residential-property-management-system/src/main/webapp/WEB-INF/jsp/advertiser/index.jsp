<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
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
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        
        .navbar {
            background: rgba(255, 255, 255, 0.95);
            padding: 1rem 2rem;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
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
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 1rem;
        }
        
        .welcome-card {
            background: white;
            border-radius: 15px;
            padding: 2rem;
            margin-bottom: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        
        .welcome-card h2 {
            color: #667eea;
            margin-bottom: 1rem;
        }
        
        .function-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 1.5rem;
        }
        
        .function-card {
            background: white;
            border-radius: 15px;
            padding: 2rem;
            text-align: center;
            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }
        
        .function-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        
        .function-card .icon {
            font-size: 3rem;
            margin-bottom: 1rem;
        }
        
        .function-card h3 {
            color: #333;
            margin-bottom: 0.5rem;
        }
        
        .function-card p {
            color: #666;
            margin-bottom: 1.5rem;
        }
        
        .function-card a {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 0.75rem 2rem;
            border-radius: 25px;
            text-decoration: none;
            transition: all 0.3s;
        }
        
        .function-card a:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>🏢 广告商管理平台</h1>
        <div class="nav-links">
            <span>欢迎，<%= user.getName() %> (<%= user.getRoleName() %>)</span>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=index">首页</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">申请入驻</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">审核状态</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=logout">退出登录</a>
        </div>
    </div>
    
    <div class="container">
        <div class="welcome-card">
            <h2>欢迎使用广告商管理平台</h2>
            <p>您可以在这里提交广告入驻申请，实时查看审核状态，管理您的广告投放。</p>
        </div>
        
        <div class="function-grid">
            <div class="function-card">
                <div class="icon">📝</div>
                <h3>申请入驻</h3>
                <p>在线提交广告入驻申请，填写相关信息</p>
                <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">立即申请</a>
            </div>
            
            <div class="function-card">
                <div class="icon">📊</div>
                <h3>审核状态</h3>
                <p>实时查看申请审核进度和结果</p>
                <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">查看状态</a>
            </div>
        </div>
    </div>
</body>
</html>
