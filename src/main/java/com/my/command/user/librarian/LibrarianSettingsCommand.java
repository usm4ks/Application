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
import java.util.List;

public class LibrarianSettingsCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(LibrarianSettingsCommand.class);

    public LibrarianSettingsCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            List<User> librarians = userDAO.getAllUsersByRole(UserRole.LIBRARIAN.getRoleName());
            request.setAttribute("librarians", librarians);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show librarians settings",e);
        }
        return "/WEB-INF/views/librarians_settings.jsp";
    }
}
