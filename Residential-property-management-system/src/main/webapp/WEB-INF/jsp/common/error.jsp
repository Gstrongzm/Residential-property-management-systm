<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>错误页面 - 小区物业管理系统</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }
        
        .error-container {
            background: white;
            border-radius: 15px;
            padding: 3rem;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            max-width: 500px;
            width: 100%;
        }
        
        .error-icon {
            font-size: 5rem;
            margin-bottom: 1rem;
        }
        
        .error-code {
            font-size: 4rem;
            color: #e74c3c;
            font-weight: bold;
            margin-bottom: 1rem;
        }
        
        .error-message {
            font-size: 1.2rem;
            color: #666;
            margin-bottom: 2rem;
        }
        
        .back-btn {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 1rem 2rem;
            border-radius: 25px;
            text-decoration: none;
            transition: all 0.3s;
        }
        
        .back-btn:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .home-link {
            display: block;
            margin-top: 1rem;
            color: #667eea;
            text-decoration: none;
        }
        
        .home-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-icon">⚠️</div>
        <div class="error-code">
            <%= request.getAttribute("jakarta.servlet.error.status_code") != null ? 
                request.getAttribute("jakarta.servlet.error.status_code") : "ERROR" %>
        </div>
        <div class="error-message">
            <%= request.getAttribute("jakarta.servlet.error.message") != null ? 
                request.getAttribute("jakarta.servlet.error.message") : "发生未知错误" %>
        </div>
        <a href="javascript:history.back()" class="back-btn">返回上一页</a>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-link">返回首页</a>
    </div>
</body>
</html>
