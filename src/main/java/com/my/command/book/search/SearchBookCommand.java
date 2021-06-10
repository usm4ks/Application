package com.my.command.book.search;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SearchBookCommand.class);

    private final BookDAO bookDAO;

    public SearchBookCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String s = request.getParameter("search");
        try {
            List<Book> books = bookDAO.searchBook(s);
            if (!books.isEmpty()) {
                request.setAttribute("book_list", books);
            } else {
                request.setAttribute("search_result", "book_is_not_found");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't search book",e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
