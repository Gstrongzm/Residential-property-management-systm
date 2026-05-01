<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>报修申请 - 小区物业管理系统</title>
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
    </style>
</head>
<body>
    <div class="navbar">
        <h1>🔧 在线报修</h1>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=index">首页</a>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=profile">个人信息</a>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=fee">缴费查询</a>
            <a href="<%= request.getContextPath() %>/servlet/ResidentServlet?action=logout">退出登录</a>
        </div>
    </div>
    
    <div class="container">
        <h2>提交维修申请</h2>
        
        <form action="<%= request.getContextPath() %>/servlet/ResidentServlet" method="post">
            <input type="hidden" name="action" value="submitRepair">
            
            <div class="form-group">
                <label for="repairType">报修类型 *</label>
                <select id="repairType" name="repairType" required>
                    <option value="">请选择报修类型</option>
                    <option value="WATER">水电维修</option>
                    <option value="ELECTRIC">电路维修</option>
                    <option value="PLUMBING">管道维修</option>
                    <option value="DOOR_WINDOW">门窗维修</option>
                    <option value="APPLIANCE">家电维修</option>
                    <option value="OTHER">其他</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="title">问题标题 *</label>
                <input type="text" id="title" name="title" required placeholder="简要描述问题">
            </div>
            
            <div class="form-group">
                <label for="description">详细描述 *</label>
                <textarea id="description" name="description" required placeholder="详细描述问题情况、发生时间等"></textarea>
            </div>
            
            <div class="form-group">
                <label for="contactPhone">联系电话 *</label>
                <input type="tel" id="contactPhone" name="contactPhone" required placeholder="请输入联系电话">
            </div>
            
            <button type="submit" class="submit-btn">提交报修申请</button>
        </form>
    </div>
</body>
</html>
