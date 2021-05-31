package com.my.command.book.edit;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditBookFormCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        EditBookFormCommand editBookFormCommand = new EditBookFormCommand(daoFactory);
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        when(daoFactory.getBookDAO().getBookById(1)).thenReturn(new Book());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("1");
        Assert.assertEquals("/WEB-INF/views/edit_book_form.jsp",editBookFormCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        EditBookFormCommand editBookFormCommand = new EditBookFormCommand(daoFactory);
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("1");
        editBookFormCommand.execute(request,response);
    }
}