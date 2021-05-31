package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookFormCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(EditBookFormCommand.class);

    public EditBookFormCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book;
        try {
            book = daoFactory.getBookDAO().getBookById(bookId);
            if (book == null){
                throw new ApplicationException("Book with this id not found",new Exception());
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show edit book form",e);
        }
        request.setAttribute("book",book);
        return "/WEB-INF/views/edit_book_form.jsp";
    }
}
