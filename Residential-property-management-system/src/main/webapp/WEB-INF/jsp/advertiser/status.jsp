<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.property.entity.User" %>
<%@ page import="com.property.entity.Advertisement" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<Advertisement> applications = (List<Advertisement>) request.getAttribute("applications");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>审核状态 - 小区物业管理系统</title>
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
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            padding: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }

        .container h2 {
            color: #667eea;
            margin-bottom: 1.5rem;
        }

        .status-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        .status-table th,
        .status-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        .status-table th {
            background: #f5f5f5;
            color: #333;
            font-weight: bold;
        }

        .status-table tr:hover {
            background: #f9f9f9;
        }

        .status-badge {
            display: inline-block;
            padding: 0.25rem 0.75rem;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: bold;
        }

        .status-pending {
            background: #fff3cd;
            color: #856404;
        }

        .status-approved {
            background: #d4edda;
            color: #155724;
        }

        .status-rejected {
            background: #f8d7da;
            color: #721c24;
        }

        .empty-message {
            text-align: center;
            padding: 3rem;
            color: #666;
        }

        .empty-message p {
            margin-bottom: 1rem;
        }

        .empty-message a {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 0.75rem 2rem;
            border-radius: 25px;
            text-decoration: none;
            transition: all 0.3s;
        }

        .empty-message a:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>📊 审核状态查询</h1>
        <div class="nav-links">
            <span>欢迎，<%= user.getName() %></span>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=index">首页</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">申请入驻</a>
            <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=logout">退出登录</a>
        </div>
    </div>

    <div class="container">
        <h2>我的申请记录</h2>

        <% if (applications != null && !applications.isEmpty()) { %>
            <table class="status-table">
                <thead>
                    <tr>
                        <th>申请编号</th>
                        <th>企业名称</th>
                        <th>广告类型</th>
                        <th>申请时间</th>
                        <th>审核状态</th>
                        <th>审核意见</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Advertisement app : applications) { %>
                        <tr>
                            <td><%= app.getApplicationId() %></td>
                            <td><%= app.getCompanyName() %></td>
                            <td>
                                <% 
                                    String adType = app.getAdType();
                                    if ("BILLBOARD".equals(adType)) out.print("户外广告牌");
                                    else if ("ELEVATOR".equals(adType)) out.print("电梯广告");
                                    else if ("COMMUNITY".equals(adType)) out.print("社区公告栏");
                                    else if ("ONLINE".equals(adType)) out.print("线上平台");
                                    else out.print("其他");
                                %>
                            </td>
                            <td><%= app.getApplyTime() %></td>
                            <td>
                                <% 
                                    String status = app.getStatus();
                                    if ("PENDING".equals(status)) {
                                        out.print("<span class=\"status-badge status-pending\">待审核</span>");
                                    } else if ("APPROVED".equals(status)) {
                                        out.print("<span class=\"status-badge status-approved\">已通过</span>");
                                    } else if ("REJECTED".equals(status)) {
                                        out.print("<span class=\"status-badge status-rejected\">已驳回</span>");
                                    }
                                %>
                            </td>
                            <td><%= (app.getReviewComment() != null && !app.getReviewComment().isEmpty()) ? app.getReviewComment() : "-" %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <div class="empty-message">
                <p>暂无申请记录</p>
                <a href="<%= request.getContextPath() %>/servlet/AdvertiserServlet?action=apply">立即申请</a>
            </div>
        <% } %>
    </div>
</body>
</html>
