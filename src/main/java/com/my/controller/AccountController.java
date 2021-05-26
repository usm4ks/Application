package com.my.controller;

import com.my.ControllerHelper;
import com.my.command.CommandConstants;
import com.my.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/account")
public class AccountController extends HttpServlet {

    private CommandFactory commandFactory;
    Set<String> availableCommands = new HashSet<>();

    @Override
    public void init(){
        commandFactory = (CommandFactory) getServletContext().getAttribute("commandFactory");
        availableCommands.add(CommandConstants.ACCOUNT);
        availableCommands.add(CommandConstants.SHOW_ALL_ORDERS);
        availableCommands.add(CommandConstants.SHOW_ALL_USERS);
        availableCommands.add(CommandConstants.SHOW_USER_INFO);
        availableCommands.add(CommandConstants.ACCEPT_ORDER_BOOK);
        availableCommands.add(CommandConstants.USERS_SETTINGS);
        availableCommands.add(CommandConstants.UNBLOCK_USER);
        availableCommands.add(CommandConstants.BLOCK_USER);
        availableCommands.add(CommandConstants.LIBRARIANS_SETTINGS);
        availableCommands.add(CommandConstants.DELETE_LIBRARIAN);
        availableCommands.add(CommandConstants.ADD_LIBRARIAN);
        availableCommands.add(CommandConstants.LOG_OUT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerHelper.doGet(req,resp,commandFactory,availableCommands);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerHelper.doPost(req, resp, commandFactory);
    }
}
