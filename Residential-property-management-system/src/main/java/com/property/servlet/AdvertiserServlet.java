package com.property.servlet;

import com.property.entity.Resident;
import com.property.entity.User;
import com.property.service.ResidentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 广告商Servlet - 处理外部广告申请企业相关请求
 */
public class AdvertiserServlet extends HttpServlet {

    private ResidentService residentService = new ResidentService();

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

        // 验证是否为广告商
        if (currentUser == null || !"ADVERTISER".equals(currentUser.getRole())) {
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
            case "apply":
                showApply(request, response);
                break;
            case "submitApply":
                submitApply(request, response);
                break;
            case "status":
                showStatus(request, response);
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
        request.getRequestDispatcher("/WEB-INF/jsp/advertiser/index.jsp").forward(request, response);
        // 获取当前用户
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        // 根据用户ID查询住户详细信息
        if (currentUser != null && currentUser.getUserId() != null) {
            Resident resident = residentService.findByUserId(currentUser.getUserId());
            // 将住户信息放入请求域中供JSP使用
            request.setAttribute("residentInfo", resident);
        }

    }

    private void showApply(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/advertiser/apply.jsp").forward(request, response);
    }

    private void submitApply(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 实现提交申请逻辑（需要创建广告申请表单实体和服务）
        // 暂时直接跳转到成功页面
        response.sendRedirect(request.getContextPath() + "/servlet/AdvertiserServlet?action=status&success=true");
    }

    private void showStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 实现查询申请状态逻辑
        request.getRequestDispatcher("/WEB-INF/jsp/advertiser/status.jsp").forward(request, response);
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
