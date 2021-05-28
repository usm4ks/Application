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

public class ShowAllBooksCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllBooksCommand.class);

    public ShowAllBooksCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null){
            try {
                page = Integer.parseInt(pageNum);
            }catch (NumberFormatException exception){
                throw new ApplicationException("Incorrect data",exception);
            }
        }
        BookDAO bookDAO = daoFactory.getBookDAO();
        if (page > 1){
            request.setAttribute("previous", String.format("book_list?command=show_all_books&page=%d", page-1));
        }
        try {
            List<Book> bookList = bookDAO.getAllBooks(page);
            request.setAttribute("book_list", bookList);
            if (bookDAO.getAllBooks(page+1).size() > 0){
                request.setAttribute("next",String.format("book_list?command=show_all_books&page=%d", page+1));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show all books", e);
        }
        return "/WEB-INF/views/book_list.jsp";
    }
}
