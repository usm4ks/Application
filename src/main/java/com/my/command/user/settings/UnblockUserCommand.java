package com.my.command.user.settings;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);
    private final UserDAO userDAO;

    public UnblockUserCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            User user = userDAO.getUserById(userId);
            if (user.isBlocked()) {
                userDAO.changeBlockStatusUserById(userId, 0);
                request.getSession().setAttribute("block_result", "unblocked");
                request.getSession().setAttribute("blocked_user_email",user.getEmail());
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't unblock user",e);
        }
        return "account?command=users_settings";
    }
}
