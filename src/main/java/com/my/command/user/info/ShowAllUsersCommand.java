package com.my.command.user.info;

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

public class ShowAllUsersCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllUsersCommand.class);

    public ShowAllUsersCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            List<User> userList = userDAO.getAllUsersByRole(UserRole.USER.getRoleName());
            request.setAttribute("user_list",userList);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Cant show all users",e);
        }

        return "/WEB-INF/views/user_list.jsp";
    }
}
