package com.my.command.book.edit;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.dao.book.impl.BookInHallDAOImpl;
import com.my.dao.book.impl.BookOnTicketDAOImpl;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteBookCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(daoFactory.getBookDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO());
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        when(daoFactory.getBookDAO().getBookById(1)).thenReturn(mock(Book.class));
        when(daoFactory.getBookInHallDAO()).thenReturn(mock(BookInHallDAOImpl.class));
        when(daoFactory.getBookInHallDAO().getBookInHallByBookId(1)).thenReturn(new ArrayList<>());
        when(daoFactory.getBookOnTicketDAO()).thenReturn(mock(BookOnTicketDAOImpl.class));
        when(daoFactory.getBookOnTicketDAO().getBookOnTicketByBookId(1)).thenReturn(new ArrayList<>());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("1");
        Assert.assertEquals("book_list?command=show_all_books",deleteBookCommand.execute(request,response));
    }

}