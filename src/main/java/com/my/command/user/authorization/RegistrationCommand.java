package com.my.command.user.authorization;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    private final UserDAO userDAO;

    public RegistrationCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        String password = request.getParameter("password");
        if (password.length() < 8 || password.length() > 18){
            throw new CommandException("Incorrect input data for password (need 8-18 symbols)", new Exception());
        }
        user.setPassword(DigestUtils.md5Hex(password));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(UserRole.USER);
        user.setBlocked(false);
        try {
            User userFromDB = userDAO.getUserByEmail(user.getEmail());
            if (userFromDB == null) {
                userDAO.registrationUser(user);
                request.getSession().setAttribute("user", user);
                return "book_list?command=show_all_books";
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't execute registration command",e);
        }
        request.getSession().setAttribute("registration_result","User with this email already exists");
        return "registration";
    }
}
