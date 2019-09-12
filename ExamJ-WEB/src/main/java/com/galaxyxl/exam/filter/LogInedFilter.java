package com.galaxyxl.exam.filter;

import com.galaxyxl.exam.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();

        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.contains("login.xhtml") || path.contains("register.xhtml")) {

            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (session.getAttribute("CUR_USER") != null) {
            User u =(User) session.getAttribute("CUR_USER");

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(request.getServletContext().getContextPath() + "/login.xhtml");
        }
    }
}
