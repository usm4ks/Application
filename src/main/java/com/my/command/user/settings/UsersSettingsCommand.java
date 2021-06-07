package com.my.command.user.settings;

import com.my.command.Command;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class UsersSettingsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UsersSettingsCommand.class);
    private final UserDAO userDAO;

    public UsersSettingsCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<User> blockedUsers = userDAO.getAllUsersByRole(UserRole.USER.getRoleName());
            blockedUsers = blockedUsers.stream().filter(User::isBlocked).collect(Collectors.toList());
            request.setAttribute("blocked_users", blockedUsers);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show users settings",e);
        }
        return "/WEB-INF/views/users_settings.jsp";
    }
}
