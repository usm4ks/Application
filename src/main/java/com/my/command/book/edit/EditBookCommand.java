package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditBookCommand.class);

    private final BookDAO bookDAO;

    public EditBookCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setPublishingHouse(request.getParameter("publishing_house"));
        book.setYear(Integer.parseInt(request.getParameter("year")));
        book.setAmount(Integer.parseInt(request.getParameter("amount")));
        try {
            bookDAO.editBook(book);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't edit book",e);
        }
        return "book_list?command=show_all_books";
    }
}
