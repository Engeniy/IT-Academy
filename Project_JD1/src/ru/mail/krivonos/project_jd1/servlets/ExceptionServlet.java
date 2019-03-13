package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("ExceptionServlet init!");
    }

    @Override
    public void destroy() {
        System.out.println("ExceptionServlet destroy!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestURI = (String) req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestURI == null) {
            requestURI = "Unknown";
        }

        System.out.println("Exception info:");
        System.out.println("The status code: " + statusCode);
        System.out.println("Servlet name: " + servletName);
        System.out.println("Exception type: " + throwable.getClass().getName());
        System.out.println("Request URI: " + requestURI);
        throwable.printStackTrace();

        String errorPage = ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ERROR_PAGE_PATH);
        req.getRequestDispatcher(errorPage).forward(req, resp);
    }
}
