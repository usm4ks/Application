package com.my.command.book.edit;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddNewBookCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        AddNewBookCommand addNewBookCommand = new AddNewBookCommand(daoFactory);
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("year")).thenReturn("1");
        when(request.getParameter("amount")).thenReturn("2");
        Assert.assertEquals("book_list?command=show_all_books",addNewBookCommand.execute(request,response));
    }
}