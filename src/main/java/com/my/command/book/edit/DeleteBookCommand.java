package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.Book;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteBookCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);


    public DeleteBookCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            Book book = daoFactory.getBookDAO().getBookById(bookId);
            List<BookInHall> bookInHallList = daoFactory.getBookInHallDAO().getBookInHallByBookId(bookId);
            List<BookOnTicket> bookOnTicketList = daoFactory.getBookOnTicketDAO().getBookOnTicketByBookId(bookId);
            if (book != null && bookOnTicketList.isEmpty() && bookInHallList.isEmpty()) {
                daoFactory.getBookDAO().deleteBook(bookId);
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't delete book",e);
        }
        return "book_list?command=show_all_books";
    }
}
