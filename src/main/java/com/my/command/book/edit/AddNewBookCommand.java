package com.my.command.book.edit;

import com.my.command.Command;
import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewBookCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddNewBookCommand.class);

    private final BookDAO bookDAO;

    public AddNewBookCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Book book = new Book();
        String title = request.getParameter("title");
        book.setTitle(title);
        book.setAuthor(request.getParameter("author"));
        book.setPublishingHouse(request.getParameter("publishing_house"));
        book.setYear(Integer.parseInt(request.getParameter("year")));
        book.setAmount(Integer.parseInt(request.getParameter("amount")));
        try {
            if (bookDAO.getBookByBookInfo(book.getTitle(), book.getAuthor(), book.getPublishingHouse(), book.getYear()) != null){
                throw new CommandException("This book already exists",new Exception());
            }
            bookDAO.insertBook(book);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't add new book",e);
        }
        request.getSession().setAttribute("add_result","new_book_was_added");
        return "book_list?command=show_all_books";
    }
}
