package com.my.command.user;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SetLangCommand extends Command {
    public SetLangCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("lang",lang);
        if (request.getSession().getAttribute("user") == null){
            return "index.jsp";
        }
        return "book_list?command=show_all_books";
    }
}
