package com.my.command.order;

import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class OrderBookCommandTest {

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        OrderBookCommand orderBookCommand = new OrderBookCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("user")).thenReturn(mock(User.class));
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenReturn(mock(User.class));
        when(daoFactory.getUserDAO().getUserById(anyInt()).isBlocked()).thenReturn(true);
        orderBookCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPathIfBookNull() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        OrderBookCommand orderBookCommand = new OrderBookCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("user")).thenReturn(mock(User.class));
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenReturn(mock(User.class));
        when(daoFactory.getUserDAO().getUserById(anyInt()).isBlocked()).thenReturn(false);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("type")).thenReturn(OrderType.IN_HALL.getType());
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        when(daoFactory.getBookDAO().getBookById(anyInt())).thenReturn(null);
        Assert.assertEquals("book_list?command=show_all_books",orderBookCommand.execute(request,response));
    }


}