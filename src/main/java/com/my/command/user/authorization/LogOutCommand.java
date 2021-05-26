package com.my.command.user.authorization;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.User;
import com.my.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand extends Command {
    public LogOutCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            request.getSession().invalidate();
        }
        return "index.jsp";
    }
}
