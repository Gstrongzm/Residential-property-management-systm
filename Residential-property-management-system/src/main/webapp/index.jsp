<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页 - 小区物业管理系统</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px; text-align: center; }
        .content { max-width: 1200px; margin: 30px auto; padding: 20px; }
        .welcome-card { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); text-align: center; }
        .welcome-card h2 { color: #333; margin-bottom: 20px; }
        .welcome-card p { color: #666; margin-bottom: 30px; }
        .btn-group { display: flex; gap: 15px; justify-content: center; flex-wrap: wrap; }
        .btn { padding: 12px 30px; border: none; border-radius: 5px; cursor: pointer; text-decoration: none; display: inline-block; transition: transform 0.2s; }
        .btn:hover { transform: translateY(-2px); }
        .btn-primary { background: #667eea; color: white; }
        .btn-success { background: #28a745; color: white; }
        .btn-info { background: #17a2b8; color: white; }
        .btn-warning { background: #ffc107; color: #333; }
        .features { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-top: 40px; }
        .feature-card { background: white; padding: 25px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .feature-card h3 { color: #333; margin-bottom: 10px; }
        .feature-card p { color: #666; font-size: 14px; }
        .footer { text-align: center; padding: 20px; color: #666; font-size: 14px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>🏠 小区物业管理系统</h1>
        <p>Residential Property Management System</p>
    </div>
    
    <div class="content">
        <div class="welcome-card">
            <h2>欢迎使用小区物业管理系统</h2>
            <p>本系统为物业管理员、小区住户、物业工作人员和外部广告申请企业提供全方位的物业管理服务</p>
            
            <div class="btn-group">
                <a href="<%= request.getContextPath() %>/login.jsp" class="btn btn-primary">用户登录</a>
            </div>
        </div>
        
        <div class="features">
            <div class="feature-card">
                <h3>👨‍💼 物业管理员</h3>
                <p>工作人员管理、住户管理、费用核算、维修任务分配、广告审核审批</p>
            </div>
            <div class="feature-card">
                <h3>🏠 小区住户</h3>
                <p>个人信息管理、在线报修、缴费查询、维修进度查看</p>
            </div>
            <div class="feature-card">
                <h3>🔧 物业工作人员</h3>
                <p>查看工作安排、同步维修进度、查看任务详情</p>
            </div>
            <div class="feature-card">
                <h3>📢 广告申请企业</h3>
                <p>线上提交广告入驻申请、实时查看审核状态</p>
            </div>
        </div>
    </div>
    
    <div class="footer">
        <p>&copy; 2024 小区物业管理系统 | 版本 1.0.0</p>
    </div>
</body>
</html>
