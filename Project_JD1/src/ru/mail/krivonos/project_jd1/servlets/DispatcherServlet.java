package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.services.impl.DatabaseInitServiceImpl;
import ru.mail.krivonos.project_jd1.servlets.command.*;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class DispatcherServlet extends HttpServlet {

    private static final Map<CommandEnum, Command> commands = new HashMap<>();


    @Override
    public void init() throws ServletException {
        System.out.println("-------- Dispatcher Servlet Init --------");
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.REGISTRATION, new RegistrationCommand());
        commands.put(CommandEnum.ITEMS, new ItemsCommand());
        commands.put(CommandEnum.ORDER, new OrderCommand());
        commands.put(CommandEnum.ORDERS, new OrdersCommand());
        commands.put(CommandEnum.PROFILE, new ProfileCommand());
        commands.put(CommandEnum.LOGOUT, new LogoutCommand());
        commands.put(CommandEnum.CREATE_ITEM, new CreateItemCommand());
        commands.put(CommandEnum.ADD_ITEM, new AddItemCommand());
        commands.put(CommandEnum.DELETE_ITEM, new DeleteItemCommand());
        commands.put(CommandEnum.UPLOAD, new UploadCommand());
        commands.put(CommandEnum.UPLOAD_XML, new UploadXMLCommand());
        commands.put(CommandEnum.UPDATE_STATE, new UpdateStateCommand());
        commands.put(CommandEnum.CHOOSE_STATE, new ChooseStateCommand());
        commands.put(CommandEnum.PROFILE_UPDATE, new ProfileUpdateCommand());
        commands.put(CommandEnum.LOGIN_REDIRECT, new LoginRedirectCommand());
        commands.put(CommandEnum.REGISTRATION_REDIRECT, new RegistrationRedirectCommand());
        DatabaseInitServiceImpl.getInstance().initDatabase();
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
        this.doPost(req, resp);
    }
}
