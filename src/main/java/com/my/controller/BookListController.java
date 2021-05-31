package com.my.controller;

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

@WebServlet("/book_list")
public class BookListController extends HttpServlet {

    private CommandFactory commandFactory;
    Set<String> availableCommands = new HashSet<>();

    @Override
    public void init(){
        commandFactory = (CommandFactory) getServletContext().getAttribute("commandFactory");
        availableCommands.add(CommandConstants.SHOW_ALL_BOOKS);
        availableCommands.add(CommandConstants.SEARCH_BOOK);
        availableCommands.add(CommandConstants.SHOW_SORTED_BOOKS);
        availableCommands.add(CommandConstants.ORDER_BOOK);
        availableCommands.add(CommandConstants.EDIT_BOOK);
        availableCommands.add(CommandConstants.EDIT_BOOK_FORM);
        availableCommands.add(CommandConstants.ADD_NEW_BOOK_FORM);
        availableCommands.add(CommandConstants.ADD_NEW_BOOK);
        availableCommands.add(CommandConstants.DELETE_BOOK);
        availableCommands.add(CommandConstants.LOG_OUT);
        availableCommands.add(CommandConstants.SET_LANG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerHelper.doGet(req,resp,commandFactory,availableCommands);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ControllerHelper.doPost(req,resp,commandFactory);
    }
}
