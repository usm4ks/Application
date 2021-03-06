package com.my.controller;

import com.my.command.Command;
import com.my.command.CommandConstants;
import com.my.command.CommandFactory;
import com.my.exception.CommandException;
import com.my.util.ControllerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private CommandFactory commandFactory;
    private static final String COMMAND = "command";
    private final Set<String> availableCommands = new HashSet<>();
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Override
    public void init(){
        commandFactory = (CommandFactory) getServletContext().getAttribute("commandFactory");
        availableCommands.add(CommandConstants.REGISTRATION);
        availableCommands.add(CommandConstants.SET_LANG);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ControllerUtils.doPost(req,resp,commandFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String stringCommand = req.getParameter(COMMAND);
        if (stringCommand == null){
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req,resp);
            return;
        }
        if (!availableCommands.contains(stringCommand)){
            req.getRequestDispatcher("/WEB-INF/views/fail.jsp").forward(req,resp);
        }
        Command command = commandFactory.getCommand(stringCommand);
        try {
            resp.sendRedirect(command.execute(req,resp));
        } catch (CommandException e) {
            LOGGER.error(e);
            req.setAttribute("error_message",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req,resp);
        }
    }
}
