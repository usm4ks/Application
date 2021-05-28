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

public class AddLibrarianCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AddLibrarianCommand.class);

    public AddLibrarianCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String userEmail = request.getParameter("librarianEmail");
        UserDAO userDAO = daoFactory.getUserDAO();
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
            throw new ApplicationException("Can't add librarian",e);
        }
        return "account?command=librarians_settings";
    }
}
