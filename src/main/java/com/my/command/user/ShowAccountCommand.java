package com.my.command.user;

import com.my.command.Command;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.order.OrderDAO;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.entities.Order;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAccountCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAccountCommand.class);
    private final OrderDAO orderDAO;
    private final BookOnTicketDAO bookOnTicketDAO;
    private final BookInHallDAO bookInHallDAO;

    public ShowAccountCommand(OrderDAO orderDAO,BookOnTicketDAO bookOnTicketDAO,BookInHallDAO bookInHallDAO) {
        this.orderDAO = orderDAO;
        this.bookOnTicketDAO = bookOnTicketDAO;
        this.bookInHallDAO = bookInHallDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (user.getRole() == UserRole.USER) {
                List<Order> orderedBooks = orderDAO.getUserOrders(user.getId());
                List<BookOnTicket> booksOnTicket = bookOnTicketDAO.getAllBooksOnUserTicket(user.getId());
                List<BookInHall> bookInHallList = bookInHallDAO.getAllUserBooksInHall(user.getId());
                request.setAttribute("ordered_books", orderedBooks);
                request.setAttribute("books_on_ticket", booksOnTicket);
                request.setAttribute("books_in_hall", bookInHallList);
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show account", e);
        }
        return "WEB-INF/views/account.jsp";
    }
}
