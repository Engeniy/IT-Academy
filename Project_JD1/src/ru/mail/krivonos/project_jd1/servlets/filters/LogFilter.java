package ru.mail.krivonos.project_jd1.servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("LogFilter Init!");
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println("#INFO: URL = " + req.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
