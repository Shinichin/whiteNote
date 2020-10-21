package com.example.wn.interceptor;

import com.example.wn.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//判断 session 中是否存在 user 属性，
// 如果存在就放行，如果不存在就跳转到 login 页面
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "index","library"
        };
        String url=request.getRequestURI();
        url= StringUtils.remove(url,contextPath+'/');//移除/字符
        String page=url;

        if(begingWith(page, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if(user==null) {
                response.sendRedirect("login");//请求重定向
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {//以requiredAuthPage开头
                result = true;
                break;
            }
        }
        return result;
    }
}
