package com.property.servlet;

import com.property.entity.User;
import com.property.service.ResidentService;
import com.property.service.StaffService;
import com.property.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录Servlet
 */
public class LoginServlet extends HttpServlet {
    
    private UserService userService = new UserService();
    private ResidentService residentService = new ResidentService();
    private StaffService staffService = new StaffService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发到登录页面
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 设置请求编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取登录参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 验证参数
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "用户名和密码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        try {
            // 调用服务层进行登录验证
            User user = userService.login(username.trim(), password.trim());
            
            if (user != null) {
                // 登录成功，将用户信息存入会话
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                
                // 更新最后登录时间
                userService.updateLastLoginTime(user.getUserId());
                
                // 根据角色跳转到不同页面
                String role = user.getRole();
                switch (role) {
                    case "ADMIN":
                        // 获取住户或工作人员详细信息
                        if ("STAFF".equals(role)) {
                            session.setAttribute("staffInfo", staffService.findByUserId(user.getUserId()));
                        }
                        response.sendRedirect(request.getContextPath() + "/servlet/AdminServlet?action=index");
                        break;
                    case "RESIDENT":
                        session.setAttribute("residentInfo", residentService.findByUserId(user.getUserId()));
                        response.sendRedirect(request.getContextPath() + "/servlet/ResidentServlet?action=index");
                        break;
                    case "STAFF":
                        session.setAttribute("staffInfo", staffService.findByUserId(user.getUserId()));
                        response.sendRedirect(request.getContextPath() + "/servlet/StaffServlet?action=index");
                        break;
                    case "ADVERTISER":
                        response.sendRedirect(request.getContextPath() + "/servlet/AdvertiserServlet?action=index");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
            } else {
                // 登录失败
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登录失败：" + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
