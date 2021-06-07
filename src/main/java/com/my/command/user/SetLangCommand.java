package com.my.command.user;

import com.my.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SetLangCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("lang",lang);
        if (request.getSession().getAttribute("user") == null){
            return "index.jsp";
        }
        return "book_list?command=show_all_books";
    }
}
