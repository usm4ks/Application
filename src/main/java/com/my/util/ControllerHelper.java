package com.my.util;

import com.my.command.Command;
import com.my.command.CommandFactory;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class ControllerHelper {

    private static final String COMMAND = "command";
    private static final Logger LOGGER = Logger.getLogger(ControllerHelper.class);

    private ControllerHelper() {}

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, CommandFactory commandFactory) throws IOException, ServletException {
        String stringCommand = req.getParameter(COMMAND);
        Command command = commandFactory.getCommand(stringCommand);
        try {
            resp.sendRedirect(command.execute(req,resp));
        } catch (ApplicationException e) {
            LOGGER.error(e);
            req.setAttribute("error_message",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req,resp);
        }
    }

    public static void doGet(HttpServletRequest req, HttpServletResponse resp, CommandFactory commandFactory, Set<String> availableCommands) throws ServletException, IOException {
        String stringCommand = req.getParameter(COMMAND);
        if (stringCommand != null && !availableCommands.contains(stringCommand)){
            req.getRequestDispatcher("/WEB-INF/views/fail.jsp").forward(req,resp);
        }
        Command command = commandFactory.getCommand(stringCommand);
        try {
            req.getRequestDispatcher(command.execute(req,resp)).forward(req,resp);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            req.setAttribute("error_message",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req,resp);
        }
    }


}
