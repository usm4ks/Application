package com.my.command.book.edit;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditBookCommandTest {


    @Test
    public void executeShouldReturnPath() throws CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        EditBookCommand editBookCommand = new EditBookCommand(daoFactory.getBookDAO());
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("year")).thenReturn("1");
        when(request.getParameter("amount")).thenReturn("2");
        Assert.assertEquals("book_list?command=show_all_books",editBookCommand.execute(request,response));
    }

}