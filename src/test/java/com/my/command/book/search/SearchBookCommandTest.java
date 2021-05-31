package com.my.command.book.search;


import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import org.junit.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchBookCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        SearchBookCommand searchBookCommand = new SearchBookCommand(daoFactory);
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("order_result")).thenReturn(null);
        Assert.assertEquals("/WEB-INF/views/book_list.jsp",searchBookCommand.execute(request,response));
    }
}