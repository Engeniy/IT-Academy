package ru.mail.krivonos.project_jd1.servlets.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
