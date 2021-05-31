package com.my.command.book.sort;

import com.my.command.Command;
import com.my.command.CommandConstants;
import com.my.command.CommandFactory;
import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import org.junit.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowSortedBooksTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowSortedBooks showSortedBooks = new ShowSortedBooks(daoFactory);
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("order_result")).thenReturn(null);
        Assert.assertEquals("/WEB-INF/views/book_list.jsp",showSortedBooks.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        CommandFactory commandFactory = new CommandFactory(daoFactory);
        Command showSortedBooks = commandFactory.getCommand(CommandConstants.SHOW_SORTED_BOOKS);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("page")).thenReturn("abc");
        HttpServletResponse response = mock(HttpServletResponse.class);
        showSortedBooks.execute(request,response);
    }

}