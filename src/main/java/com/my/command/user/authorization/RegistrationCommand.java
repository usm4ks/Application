package com.my.command.user.authorization;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    public RegistrationCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        if (user.getPassword().length() < 6 || user.getPassword().length() > 18){
            throw new ApplicationException("Incorrect input data for password (need 6-18 symbols)", new Exception());
        }
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(UserRole.USER);
        user.setBlocked(false);
        try {
            User userFromBd = userDAO.getUserByEmail(user.getEmail());
            if (userFromBd == null) {
                userDAO.registrationUser(user);
                request.getSession().setAttribute("user", user);
                return "book_list?command=show_all_books&page=1";
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't execute registration command",e);
        }
        request.getSession().setAttribute("registration_result","User with this email already exists");
        return "registration";
    }
}
