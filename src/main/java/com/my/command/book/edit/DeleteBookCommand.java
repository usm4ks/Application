package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.entities.Book;
import com.my.entities.BookInHall;
import com.my.entities.BookOnTicket;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);

    private final BookDAO bookDAO;
    private final BookOnTicketDAO bookOnTicketDAO;
    private final BookInHallDAO bookInHallDAO;



    public DeleteBookCommand(BookDAO bookDAO,BookOnTicketDAO bookOnTicketDAO,BookInHallDAO bookInHallDAO) {
        this.bookDAO = bookDAO;
        this.bookOnTicketDAO = bookOnTicketDAO;
        this.bookInHallDAO = bookInHallDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            Book book = bookDAO.getBookById(bookId);
            List<BookInHall> bookInHallList = bookInHallDAO.getBookInHallByBookId(bookId);
            List<BookOnTicket> bookOnTicketList = bookOnTicketDAO.getBookOnTicketByBookId(bookId);
            if (book != null && bookOnTicketList.isEmpty() && bookInHallList.isEmpty()) {
                bookDAO.deleteBook(bookId);
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't delete book",e);
        }
        return "book_list?command=show_all_books";
    }
}
