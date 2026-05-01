package com.property.filter;

import jakarta.servlet.*;
import java.io.IOException;

/**
 * 全局编码过滤器
 * 设置请求和响应的字符编码为UTF-8，防止中文乱码
 */
public class EncodingFilter implements Filter {
    
    private String encoding = "UTF-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 从配置中获取编码，如果没有则使用默认值
        String enc = filterConfig.getInitParameter("encoding");
        if (enc != null && !enc.isEmpty()) {
            encoding = enc;
        }
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // 设置请求编码
        request.setCharacterEncoding(encoding);
        
        // 设置响应编码
        response.setContentType("text/html;charset=" + encoding);
        response.setCharacterEncoding(encoding);
        
        // 继续执行过滤器链
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // 销毁操作
    }
}
