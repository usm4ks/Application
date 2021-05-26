package com.my.command.user.librarian;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteLibrarianCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteLibrarianCommand.class);

    public DeleteLibrarianCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.getUserById(userId);
            if (user.getRole().equals(UserRole.LIBRARIAN)) {
                userDAO.changeRoleUserById(userId, UserRole.USER.getRoleName());
                request.getSession().setAttribute("add_result", "User " + user.getEmail() + " is not librarian");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't delete librarian",e);
        }
        return "account?command=librarians_settings";
    }
}
