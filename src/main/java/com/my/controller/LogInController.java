package com.my.controller;

import com.my.command.CommandConstants;
import com.my.command.CommandFactory;
import com.my.util.ControllerHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/log_in")
public class LogInController extends HttpServlet {
    private CommandFactory commandFactory;
    private final Set<String> availableCommands = new HashSet<>();

    @Override
    public void init(){
        commandFactory = (CommandFactory) getServletContext().getAttribute("commandFactory");
        availableCommands.add(CommandConstants.LOG_IN);
        availableCommands.add(CommandConstants.SET_LANG);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ControllerHelper.doPost(req,resp,commandFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerHelper.doGet(req,resp,commandFactory,availableCommands);
    }
}
