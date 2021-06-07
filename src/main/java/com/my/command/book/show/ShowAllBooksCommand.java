package com.my.command.book.show;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import com.my.util.PaginationUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ShowAllBooksCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllBooksCommand.class);

    private final BookDAO bookDAO;

    public ShowAllBooksCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            int page = PaginationUtils.getPage(request);
            if (page > 1){
                request.setAttribute("previous", String.format("book_list?command=show_all_books&page=%d", page-1));
            }
            List<Book> bookList = bookDAO.getAllBooks(Optional.of(page));
            request.setAttribute("book_list", bookList);
            if (!bookDAO.getAllBooks(Optional.of(page+1)).isEmpty()){
                request.setAttribute("next",String.format("book_list?command=show_all_books&page=%d", page+1));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show all books", e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
