package com.my.command.user.librarian;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianSettingsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LibrarianSettingsCommand.class);

    private final UserDAO userDAO;

    public LibrarianSettingsCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<User> librarians = userDAO.getAllUsersByRole(UserRole.LIBRARIAN.getRoleName());
            request.setAttribute("librarians", librarians);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new CommandException("Can't show librarians settings",e);
        }
        return "/WEB-INF/views/librarians_settings.jsp";
    }
}
