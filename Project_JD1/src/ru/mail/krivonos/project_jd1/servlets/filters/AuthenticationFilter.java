package ru.mail.krivonos.project_jd1.servlets.filters;

import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {

    private static final Set<CommandEnum> CUSTOMER_AVAILABLE = new HashSet<>();
    private static final Set<CommandEnum> SALE_AVAILABLE = new HashSet<>();
    private static final Set<CommandEnum> EVERYBODY_AVAILABLE = new HashSet<>();
    private static final String LOGIN_PATH = "/index.jsp";

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("AuthenticationFilter init!");
        EVERYBODY_AVAILABLE.add(CommandEnum.REGISTRATION);
        EVERYBODY_AVAILABLE.add(CommandEnum.REGISTRATION_REDIRECT);
        EVERYBODY_AVAILABLE.add(CommandEnum.LOGIN);
        EVERYBODY_AVAILABLE.add(CommandEnum.LOGIN_REDIRECT);
        CUSTOMER_AVAILABLE.add(CommandEnum.ITEMS);
        CUSTOMER_AVAILABLE.add(CommandEnum.ORDER);
        CUSTOMER_AVAILABLE.add(CommandEnum.ORDERS);
        CUSTOMER_AVAILABLE.add(CommandEnum.PROFILE);
        CUSTOMER_AVAILABLE.add(CommandEnum.PROFILE_UPDATE);
        CUSTOMER_AVAILABLE.add(CommandEnum.LOGOUT);
        SALE_AVAILABLE.add(CommandEnum.ITEMS);
        SALE_AVAILABLE.add(CommandEnum.DELETE_ITEM);
        SALE_AVAILABLE.add(CommandEnum.CREATE_ITEM);
        SALE_AVAILABLE.add(CommandEnum.ADD_ITEM);
        SALE_AVAILABLE.add(CommandEnum.UPLOAD);
        SALE_AVAILABLE.add(CommandEnum.ORDERS);
        SALE_AVAILABLE.add(CommandEnum.UPDATE_STATE);
        SALE_AVAILABLE.add(CommandEnum.CHOOSE_STATE);
        SALE_AVAILABLE.add(CommandEnum.PROFILE);
        SALE_AVAILABLE.add(CommandEnum.PROFILE_UPDATE);
    }

    @Override
    public void destroy() {
        System.out.println("AuthenticationFilter destroyed");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String command = req.getParameter("command");
        if (session == null) {
            defaultFilter(servletRequest, servletResponse, filterChain, req, resp, command);
        } else {
            AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
            if (authorizedUser == null) {
                defaultFilter(servletRequest, servletResponse, filterChain, req, resp, command);
            } else {
                CommandEnum commandEnum = CommandEnum.getCommand(command);
                PermissionsEnum permission = authorizedUser.getPermission();
                switch (permission) {
                    case CUSTOMER_PERMISSION:
                        if (CUSTOMER_AVAILABLE.contains(commandEnum)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            session.removeAttribute(Constants.SESSION_USER_KEY);
                            resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
                        }
                        break;
                    case SALE_PERMISSION:
                        if (SALE_AVAILABLE.contains(commandEnum)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            session.removeAttribute(Constants.SESSION_USER_KEY);
                            resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
                        }
                        break;
                    default:
                        session.removeAttribute(Constants.SESSION_USER_KEY);
                        resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
                }
            }

        }
    }

    private void defaultFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                               FilterChain filterChain, HttpServletRequest req, HttpServletResponse resp,
                               String command) throws IOException, ServletException {
        if (req.getMethod().equals("POST")) {
            CommandEnum commandEnum = CommandEnum.getCommand(command);
            if (EVERYBODY_AVAILABLE.contains(commandEnum)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
        }
    }
}
