package com.my.command.book.sort;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.util.PaginationHelper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowSortedBooks extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowSortedBooks.class);

    public ShowSortedBooks(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int page = PaginationHelper.getPage(request);
        String sortBy = request.getParameter("sort_by");
        BookDAO bookDAO = daoFactory.getBookDAO();
        if (page > 1){
            request.setAttribute("previous", String.format("book_list?command=show_sorted_books&sort_by=%s&page=%d",sortBy,page-1));
        }
        try {
            List<Book> bookList = bookDAO.getSortedBooks(page,sortBy);
            request.setAttribute("book_list", bookList);
            if (!bookDAO.getSortedBooks(page+1,sortBy).isEmpty()){
                request.setAttribute("next",String.format("book_list?command=show_sorted_books&sort_by=%s&page=%d",sortBy ,page+1));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show sorted books",e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
