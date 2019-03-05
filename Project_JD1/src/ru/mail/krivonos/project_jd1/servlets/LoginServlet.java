package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getInstance();
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userLoginDTO.setEmail(login);
        userLoginDTO.setPassword(password);
        AuthorizedUserDTO authorizedUserDTO = userService.loginUser(userLoginDTO);
    }
}
