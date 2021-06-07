package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookFormCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditBookFormCommand.class);

    private final BookDAO bookDAO;

    public EditBookFormCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book;
        try {
            book = bookDAO.getBookById(bookId);
            if (book == null){
                throw new CommandException("Book with this id not found",new Exception());
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show edit book form",e);
        }
        request.setAttribute("book",book);
        return "/WEB-INF/views/edit_book_form.jsp";
    }
}
