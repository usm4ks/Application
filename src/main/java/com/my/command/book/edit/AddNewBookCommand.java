package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewBookCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AddNewBookCommand.class);

    public AddNewBookCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Book book = new Book();
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setPublishingHouse(request.getParameter("publishing_house"));
        book.setYear(Integer.parseInt(request.getParameter("year")));
        book.setAmount(Integer.parseInt(request.getParameter("amount")));
        if (daoFactory.getBookDAO().getBookByBookInfo(book.getTitle(), book.getAuthor(), book.getPublishingHouse(), book.getYear()) != null){
            throw new ApplicationException("This book already exists",new Exception());
        }
        try {
            daoFactory.getBookDAO().insertBook(book);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't add new book",e);
        }
        return "book_list?command=show_all_books";
    }
}
