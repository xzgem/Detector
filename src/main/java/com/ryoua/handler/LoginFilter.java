package com.ryoua.handler;

import com.ryoua.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getServletPath();
        User sysUser = (User) WebUtils.getSessionAttribute(req,"user");
        UserLocal.add(sysUser);
        UserLocal.add(req);
        chain.doFilter(request,response);
        return;
    }

    @Override
    public void destroy() {

    }
}
