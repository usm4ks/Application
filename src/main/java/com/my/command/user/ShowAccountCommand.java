package com.my.command.user;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.order.OrderDAO;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.entities.Order;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAccountCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAccountCommand.class);

    public ShowAccountCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            if (user.getRole() == UserRole.LIBRARIAN) {
                OrderDAO orderDAO = daoFactory.getOrderDAO();
                request.setAttribute("orders", orderDAO.getAllOrders());
            } else if (user.getRole() == UserRole.USER) {
                OrderDAO orderDAO = daoFactory.getOrderDAO();
                List<Order> orderedBooks = orderDAO.getUserOrders(user.getId());
                List<BookOnTicket> booksOnTicket = daoFactory.getBookOnTicketDAO().getAllBooksOnUserTicket(user.getId());
                List<BookInHall> bookInHallList = daoFactory.getBookInHallDAO().getAllUserBooksInHall(user.getId());
                session.setAttribute("ordered_books", orderedBooks);
                session.setAttribute("books_on_ticket", booksOnTicket);
                session.setAttribute("books_in_hall", bookInHallList);
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show account",e);
        }
        return "WEB-INF/views/account.jsp";
    }
}
