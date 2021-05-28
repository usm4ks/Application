package com.my.command.book;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.dao.DAOFactory;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchBookCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(SearchBookCommand.class);

    public SearchBookCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        if (null != request.getSession().getAttribute("order_result")){
            request.getSession().removeAttribute("order_result");
        }
        BookDAO bookDAO = daoFactory.getBookDAO();
        String s = request.getParameter("search");
        try {
            List<Book> books = bookDAO.searchBook(s);
            if (books.size() != 0) {
                request.setAttribute("book_list", books);
            } else {
                request.setAttribute("search_result", "book_is_not_found");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't search book",e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
