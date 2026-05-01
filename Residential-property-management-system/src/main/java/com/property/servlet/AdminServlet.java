package com.property.servlet;

import com.property.entity.User;
import com.property.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * 管理员Servlet - 处理物业管理员相关请求
 */
public class AdminServlet extends HttpServlet {
    
    private UserService userService = new UserService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取当前用户
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;
        
        // 验证是否为管理员
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "index";
        }
        
        switch (action) {
            case "index":
                showIndex(request, response);
                break;
            case "userList":
                showUserList(request, response);
                break;
            case "addUser":
                addUser(request, response);
                break;
            case "editUser":
                editUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                showIndex(request, response);
        }
    }
    
    private void showIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin/index.jsp").forward(request, response);
    }
    
    private void showUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = request.getParameter("role");
        List<User> userList = userService.findAll(role);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/user_list.jsp").forward(request, response);
    }
    
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 实现添加用户逻辑
        response.sendRedirect(request.getContextPath() + "/servlet/AdminServlet?action=userList");
    }
    
    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 实现编辑用户逻辑
        response.sendRedirect(request.getContextPath() + "/servlet/AdminServlet?action=userList");
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 实现删除用户逻辑
        response.sendRedirect(request.getContextPath() + "/servlet/AdminServlet?action=userList");
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
