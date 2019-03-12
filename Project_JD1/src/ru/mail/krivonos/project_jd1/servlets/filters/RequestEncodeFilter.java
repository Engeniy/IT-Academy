package ru.mail.krivonos.project_jd1.servlets.filters;

import javax.servlet.*;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("RequestEncodeFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("RequestEncodeFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setContentType("text/html; charset=UTF-8");
    }
}
