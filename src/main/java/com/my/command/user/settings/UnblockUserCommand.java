package com.my.command.user.settings;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);

    public UnblockUserCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.getUserById(userId);
            if (user.isBlocked()) {
                userDAO.changeBlockStatusUserById(userId, 0);
                request.getSession().setAttribute("block_result", "User " + user.getEmail() + " unblocked");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't unblock user",e);
        }
        return "account?command=users_settings";
    }
}
