package com.my.command.order;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.*;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.services.OrderBookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class OrderBookCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(OrderBookCommand.class);

    public OrderBookCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.isBlocked()){
            throw new ApplicationException("Blocked user can`t order book",new Exception());
        }
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String type = request.getParameter("type");
        try {
            request.getSession().setAttribute("order_result", checkAndMakeOrder(user.getId(), bookId, type));
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't execute order book command",e);
        }
        return "book_list?command=show_all_books";
    }

    private String checkAndMakeOrder(int userId,int bookId,String type) throws ApplicationException {
        Book book = daoFactory.getBookDAO().getBookById(bookId);
        if (book == null){
            return "Book not found";
        }
        BookOnTicket bookOnTicket = daoFactory.getBookOnTicketDAO().getBookOnUserTicket(userId,bookId);
        if (bookOnTicket != null){
            return "Book is already on your ticket";
        }
        BookInHall bookInHall = daoFactory.getBookInHallDAO().getUserBookInHall(userId, bookId);
        if (bookInHall != null){
            return "You have this book in hall";
        }
        Order userOrder = daoFactory.getOrderDAO().getUserOrder(userId, bookId);
        if (userOrder != null){
            return "Book is already ordered";
        }
        if (book.getAmount() > 0){
            OrderBookService.getInstance().orderBook(userId,bookId,OrderType.valueOf(type.toUpperCase(Locale.ROOT)));
            return "Book was added";
        }
        return "Book is not added ;(";
    }
}
