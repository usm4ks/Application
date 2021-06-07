package com.my.command.user.authorization;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LogInCommand.class);

    private final UserDAO userDAO;

    public LogInCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            user = userDAO.getUserByEmail(email);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't execute log in command",e);
        }
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            request.getSession().setAttribute("user",user);
            return "book_list?command=show_all_books&page=1";
        }
        request.getSession().setAttribute("log_in_result","Incorrect email or password");
        return "index.jsp";
    }
}
