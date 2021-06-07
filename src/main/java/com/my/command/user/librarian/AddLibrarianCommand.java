package com.my.command.user.librarian;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddLibrarianCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddLibrarianCommand.class);
    private final UserDAO userDAO;

    public AddLibrarianCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String userEmail = request.getParameter("librarianEmail");
        try {
            User user = userDAO.getUserByEmail(userEmail);
            if (user == null) {
                request.getSession().setAttribute("add_result", "user_with_this_email_not_found");
            } else if (user.getRole().equals(UserRole.USER)) {
                userDAO.changeRoleUserById(user.getId(), UserRole.LIBRARIAN.getRoleName());
                request.getSession().setAttribute("add_result", "user_is_librarian_now");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't add librarian",e);
        }
        return "account?command=librarians_settings";
    }
}
