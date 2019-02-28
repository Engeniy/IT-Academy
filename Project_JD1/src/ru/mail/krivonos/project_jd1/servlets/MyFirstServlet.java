package ru.mail.krivonos.project_jd1.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String field = req.getParameter("meow");
        resp.getWriter().println("<html>\n" +
                "  <head>\n" +
                "    <title>$Title$</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  <h1>MEOW!</h1>\n" +
                field +
                "  </body>\n" +
                "</html>");*/
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}
