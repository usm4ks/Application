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

public class DeleteLibrarianCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteLibrarianCommand.class);

    private final UserDAO userDAO;

    public DeleteLibrarianCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            User user = userDAO.getUserById(userId);
            if (user.getRole().equals(UserRole.LIBRARIAN)) {
                userDAO.changeRoleUserById(userId, UserRole.USER.getRoleName());
                request.getSession().setAttribute("add_result", "user_is_not_librarian_more");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't delete librarian",e);
        }
        return "account?command=librarians_settings";
    }
}
