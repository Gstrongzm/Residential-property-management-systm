package com.property.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录拦截过滤器
 * 用于检查用户是否已登录，未登录则重定向到登录页面
 */
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 获取请求URI
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        
        // 放行登录页面、静态资源等
        if (uri.equals(contextPath + "/login.jsp") || 
            uri.equals(contextPath + "/index.jsp") ||
            uri.equals(contextPath + "/") ||
            uri.endsWith(".css") ||
            uri.endsWith(".js") ||
            uri.endsWith(".jpg") ||
            uri.endsWith(".png") ||
            uri.endsWith(".gif") ||
            uri.endsWith(".ico") ||
            uri.contains("/servlet/LoginServlet")) {
            chain.doFilter(request, response);
            return;
        }
        
        // 检查会话中是否有用户信息
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null) {
            // 已登录，继续执行
            chain.doFilter(request, response);
        } else {
            // 未登录，重定向到登录页面
            httpResponse.sendRedirect(contextPath + "/login.jsp");
        }
    }
    
    @Override
    public void destroy() {
        // 销毁操作
    }
}
