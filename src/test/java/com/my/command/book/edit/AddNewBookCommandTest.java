package com.my.command.book.edit;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddNewBookCommandTest {

    //@mock mock(BookDAOImpl.class)

    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        DAOFactory daoFactory = mock(DAOFactory.class);
        AddNewBookCommand addNewBookCommand = new AddNewBookCommand(daoFactory.getBookDAO());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        when(request.getParameter("year")).thenReturn("1");
        when(request.getParameter("amount")).thenReturn("2");

        //then
        assertEquals("book_list?command=show_all_books",addNewBookCommand.execute(request,response));
    }
}