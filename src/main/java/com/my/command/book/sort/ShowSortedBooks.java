package com.my.command.book.sort;

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

public class ShowSortedBooks implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowSortedBooks.class);

    private final BookDAO bookDAO;

    public ShowSortedBooks(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String sortBy = request.getParameter("sort_by");
        try {
            int page = PaginationUtils.getPage(request);
            if (page > 1){
                request.setAttribute("previous", String.format("book_list?command=show_sorted_books&sort_by=%s&page=%d",sortBy,page-1));
            }
            List<Book> bookList = bookDAO.getSortedBooks(page,sortBy);
            request.setAttribute("book_list", bookList);
            if (!bookDAO.getSortedBooks(page+1,sortBy).isEmpty()){
                request.setAttribute("next",String.format("book_list?command=show_sorted_books&sort_by=%s&page=%d",sortBy ,page+1));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show sorted books",e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
