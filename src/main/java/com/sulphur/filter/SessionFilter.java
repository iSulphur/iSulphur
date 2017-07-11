package com.sulphur.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[] { "/admin/login.html", "/index.jsp", "/admin/admin.do","/admin/team.do","/admin/task.do"};

        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = chek(notFilter,uri); 
        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("admin_id");
            if (null == obj) {
                PrintWriter out = response.getWriter();  
                String loginPage = request.getContextPath()+"/admin/login.html";  
                StringBuilder builder = new StringBuilder();  
                builder.append("<script type=\"text/javascript\">"); 
                builder.append("alert('Not Login!');");  
                builder.append("window.top.location.href='");  
                builder.append(loginPage);  
                builder.append("';");  
                builder.append("</script>");  
                out.print(builder.toString());
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }

    public boolean chek(String[] notFilter,String url){  
        //url以css和js结尾的不进行拦截  
        if(url.endsWith(".css") || url.endsWith(".js")){  
            return false;  
        }  
        //含有notFilter中的任何一个则不进行拦截  
        for (String s : notFilter) {  
            if (url.indexOf(s) != -1) {  
                return false;  
            }  
        }  
        return true;  
    }  
}