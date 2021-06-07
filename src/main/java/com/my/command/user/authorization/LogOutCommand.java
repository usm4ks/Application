package com.my.command.user.authorization;

import com.my.command.Command;
import com.my.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            request.getSession().invalidate();
        }
        return "index.jsp";
    }
}
