package com.my.command.user.settings;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.user.UserDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(BlockUserCommand.class);

    public BlockUserCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String userEmail = request.getParameter("userEmail");
        UserDAO userDAO = daoFactory.getUserDAO();
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
            throw new ApplicationException("Can't block user",e);
        }

        return "account?command=users_settings";
    }
}
