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

public class BlockUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(BlockUserCommand.class);
    private final UserDAO userDAO;

    public BlockUserCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String userEmail = request.getParameter("userEmail");
        try {
            User user = userDAO.getUserByEmail(userEmail);
            if (user == null){
                request.getSession().setAttribute("block_result","user_with_this_email_not_found");
            }
            else if (user.getRole().equals(UserRole.USER) && !user.isBlocked()){
                userDAO.changeBlockStatusUserById(user.getId(),1);
                request.getSession().setAttribute("block_result","blocked");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't block user",e);
        }

        return "account?command=users_settings";
    }
}
