package com.my.command.order;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.order.OrderDAO;
import com.my.dao.user.UserDAO;
import com.my.entities.*;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import com.my.services.OrderBookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class OrderBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderBookCommand.class);

    private final UserDAO userDAO;
    private final BookDAO bookDAO;
    private final BookOnTicketDAO bookOnTicketDAO;
    private final BookInHallDAO bookInHallDAO;
    private final OrderDAO orderDAO;


    public OrderBookCommand(UserDAO userDAO,BookDAO bookDAO,BookOnTicketDAO bookOnTicketDAO,BookInHallDAO bookInHallDAO,OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
        this.bookOnTicketDAO = bookOnTicketDAO;
        this.bookInHallDAO = bookInHallDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            if (null != request.getSession().getAttribute("search_result")){
                request.getSession().removeAttribute("search_result");
            }
            User user = (User) request.getSession().getAttribute("user");
            if (userDAO.getUserById(user.getId()).isBlocked()){
                throw new CommandException("Blocked user can`t order book",new Exception());
            }
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            String type = request.getParameter("type");
            request.getSession().setAttribute("order_result", checkAndMakeOrder(user.getId(), bookId, type));
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't execute order book command",e);
        }
        return "book_list?command=show_all_books";
    }

    private String checkAndMakeOrder(int userId,int bookId,String type) throws ApplicationException {
        Book book = bookDAO.getBookById(bookId);
        if (book == null){
            return "book_not_found";
        }
        BookOnTicket bookOnTicket = bookOnTicketDAO.getBookOnUserTicket(userId,bookId);
        if (bookOnTicket != null){
            return "book_is_already_on_your_ticket";
        }
        BookInHall bookInHall = bookInHallDAO.getUserBookInHall(userId, bookId);
        if (bookInHall != null){
            return "you_have_this_book_in_hall";
        }
        Order userOrder = orderDAO.getUserOrder(userId, bookId);
        if (userOrder != null){
            return "book_is_already_ordered";
        }
        if (book.getAmount() > 0){
            OrderBookService.getInstance().orderBook(userId,bookId,OrderType.valueOf(type.toUpperCase(Locale.ROOT)));
            return "book_was_added";
        }
        return "book_is_not_added";
    }
}
