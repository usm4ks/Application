package com.my.command.user.info;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserInfoCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUserInfoCommand.class);

    public ShowUserInfoCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            User user = daoFactory.getUserDAO().getUserById(userId);
            List<BookOnTicket> bookOnTicketList = daoFactory.getBookOnTicketDAO().getAllBooksOnUserTicket(userId);
            List<BookInHall> bookInHallList = daoFactory.getBookInHallDAO().getAllUserBooksInHall(userId);
            request.setAttribute("user_info", user);
            request.setAttribute("user_ticket_info", bookOnTicketList);
            request.setAttribute("user_in_hall_info", bookInHallList);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show user information",e);
        }
        return "/WEB-INF/views/user_info.jsp";
    }
}
