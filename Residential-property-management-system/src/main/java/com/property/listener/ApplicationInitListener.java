package com.property.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * 项目启动监听器
 * 在项目启动时执行初始化操作
 */
@WebListener
public class ApplicationInitListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        // 设置应用名称
        context.setAttribute("appName", "小区物业管理系统");
        
        // 设置系统版本
        context.setAttribute("appVersion", "1.0.0");
        
        // 初始化在线用户数
        context.setAttribute("onlineUserCount", 0);
        
        System.out.println("========================================");
        System.out.println("小区物业管理系统启动成功！");
        System.out.println("应用名称：" + context.getAttribute("appName"));
        System.out.println("版本：" + context.getAttribute("appVersion"));
        System.out.println("========================================");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        System.out.println("========================================");
        System.out.println("小区物业管理系统关闭！");
        System.out.println("应用名称：" + context.getAttribute("appName"));
        System.out.println("========================================");
    }
}
