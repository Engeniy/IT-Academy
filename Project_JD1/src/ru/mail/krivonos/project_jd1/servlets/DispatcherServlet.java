package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.servlets.command.Command;
import ru.mail.krivonos.project_jd1.servlets.command.ItemsCommand;
import ru.mail.krivonos.project_jd1.servlets.command.LoginCommand;
import ru.mail.krivonos.project_jd1.servlets.command.RegistrationCommand;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private static final Map<CommandEnum, Command> commands = new HashMap<>();



    @Override
    public void init() throws ServletException {
        System.out.println("-------- Dispatcher Servlet Init --------");
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.REGISTRATION, new RegistrationCommand());
        commands.put(CommandEnum.ITEMS, new ItemsCommand());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        System.out.println("Command: " + command + " got!");
        Command commandAction = commands.get(CommandEnum.getCommand(command));
        if (commandAction != null) {
            String page = commandAction.execute(req, resp);
            if (page != null) {
                getServletContext().getRequestDispatcher(page).forward(req, resp);
            }
        } else {
            System.out.println("Command " + command + " does not exists!");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
