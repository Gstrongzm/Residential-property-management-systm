<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>申请入驻 - 小区物业管理系统</title>
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
        
        .form-container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            padding: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        
        .form-container h2 {
            color: #667eea;
            margin-bottom: 1.5rem;
            text-align: center;
        }
        
        .form-group {
            margin-bottom: 1.5rem;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #333;
            font-weight: bold;
        }
        
        .form-group input,
        .form-group textarea,
        .form-group select {
            width: 100%;
            padding: 0.75rem;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }
        
        .form-group input:focus,
        .form-group textarea:focus,
        .form-group select:focus {
            outline: none;
            border-color: #667eea;
        }
        
        .form-group textarea {
            min-height: 120px;
            resize: vertical;
        }
        
        .submit-btn {
            width: 100%;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 1rem;
            border: none;
            border-radius: 8px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        
        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .message {
            padding: 1rem;
            margin-bottom: 1.5rem;
            border-radius: 8px;
            text-align: center;
        }
        
        .message.success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .message.error {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>📝 广告入驻申请</h1>
        <div class="nav-links">
            <span>欢迎，<%= user.getName() %></span>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=index">首页</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=status">审核状态</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=logout">退出登录</a>
        </div>
    </div>
    
    <div class="form-container">
        <h2>提交广告入驻申请</h2>
        
        <% if ("true".equals(success)) { %>
            <div class="message success">申请提交成功！请等待管理员审核。</div>
        <% } %>
        
        <% if (error != null && !error.isEmpty()) { %>
            <div class="message error"><%= error %></div>
        <% } %>
        
        <form action="<%= request.getContextPath() %>/servlet/AdvertiserServlet" method="post">
            <input type="hidden" name="action" value="submitApply">
            
            <div class="form-group">
                <label for="companyName">企业名称 *</label>
                <input type="text" id="companyName" name="companyName" required placeholder="请输入企业全称">
            </div>
            
            <div class="form-group">
                <label for="contactPerson">联系人 *</label>
                <input type="text" id="contactPerson" name="contactPerson" required placeholder="请输入联系人姓名">
            </div>
            
            <div class="form-group">
                <label for="contactPhone">联系电话 *</label>
                <input type="tel" id="contactPhone" name="contactPhone" required placeholder="请输入联系电话">
            </div>
            
            <div class="form-group">
                <label for="email">电子邮箱 *</label>
                <input type="email" id="email" name="email" required placeholder="请输入电子邮箱">
            </div>
            
            <div class="form-group">
                <label for="adType">广告类型 *</label>
                <select id="adType" name="adType" required>
                    <option value="">请选择广告类型</option>
                    <option value="BILLBOARD">户外广告牌</option>
                    <option value="ELEVATOR">电梯广告</option>
                    <option value="COMMUNITY">社区公告栏</option>
                    <option value="ONLINE">线上平台</option>
                    <option value="OTHER">其他</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="adContent">广告内容描述 *</label>
                <textarea id="adContent" name="adContent" required placeholder="请详细描述广告内容、尺寸、投放周期等信息"></textarea>
            </div>
            
            <div class="form-group">
                <label for="remark">备注说明</label>
                <textarea id="remark" name="remark" placeholder="其他需要说明的信息（可选）"></textarea>
            </div>
            
            <button type="submit" class="submit-btn">提交申请</button>
        </form>
    </div>
</body>
</html>
