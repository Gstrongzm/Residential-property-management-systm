package com.property.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * 会话监听器
 * 用于统计在线用户数量
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        
        // 获取当前在线用户数
        Integer onlineCount = (Integer) context.getAttribute("onlineUserCount");
        if (onlineCount == null) {
            onlineCount = 0;
        }
        
        // 在线用户数加1
        context.setAttribute("onlineUserCount", onlineCount + 1);
        
        System.out.println("新会话创建，当前在线用户数：" + (onlineCount + 1));
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        
        // 获取当前在线用户数
        Integer onlineCount = (Integer) context.getAttribute("onlineUserCount");
        if (onlineCount == null || onlineCount <= 0) {
            onlineCount = 0;
        } else {
            onlineCount--;
        }
        
        // 更新在线用户数
        context.setAttribute("onlineUserCount", onlineCount);
        
        System.out.println("会话销毁，当前在线用户数：" + onlineCount);
    }
}
