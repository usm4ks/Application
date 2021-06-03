package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewBookFormCommand extends Command {


    public AddNewBookFormCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/views/add_new_book_form.jsp";
    }
}
