package com.my.command.user.info;

import com.my.command.Command;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.user.UserDAO;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserInfoCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUserInfoCommand.class);

    private final UserDAO userDAO;
    private final BookOnTicketDAO bookOnTicketDAO;
    private final BookInHallDAO bookInHallDAO;


    public ShowUserInfoCommand(UserDAO userDAO, BookOnTicketDAO bookOnTicketDAO, BookInHallDAO bookInHallDAO) {
        this.userDAO = userDAO;
        this.bookOnTicketDAO = bookOnTicketDAO;
        this.bookInHallDAO = bookInHallDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            User user = userDAO.getUserById(userId);
            List<BookOnTicket> bookOnTicketList = bookOnTicketDAO.getAllBooksOnUserTicket(userId);
            List<BookInHall> bookInHallList = bookInHallDAO.getAllUserBooksInHall(userId);
            request.setAttribute("user_info", user);
            request.setAttribute("user_ticket_info", bookOnTicketList);
            request.setAttribute("user_in_hall_info", bookInHallList);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show user information",e);
        }
        return "/WEB-INF/views/user_info.jsp";
    }
}
