package com.my.command.order;

import com.my.dao.DAOFactory;
import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.dao.order.OrderDAO;
import com.my.dao.user.UserDAO;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderBookCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    BookDAO bookDAO;
    @Mock
    BookOnTicketDAO bookOnTicketDAO;
    @Mock
    BookInHallDAO bookInHallDAO;
    @Mock
    OrderDAO orderDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException, CommandException {
        //given
        OrderBookCommand orderBookCommand = new OrderBookCommand(userDAO,bookDAO,bookOnTicketDAO,bookInHallDAO,orderDAO);
        User user = new User();
        user.setBlocked(true);

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(new User());
        when(userDAO.getUserById(anyInt())).thenReturn(user);

        //then
        orderBookCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPathIfBookNull() throws ApplicationException,CommandException {
        //given
        OrderBookCommand orderBookCommand = new OrderBookCommand(userDAO,bookDAO,bookOnTicketDAO,bookInHallDAO,orderDAO);
        User user = new User();
        user.setBlocked(false);

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(new User());
        when(userDAO.getUserById(anyInt())).thenReturn(user);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("type")).thenReturn(OrderType.IN_HALL.getType());
        when(bookDAO.getBookById(anyInt())).thenReturn(null);

        //then
        assertEquals("book_list?command=show_all_books",orderBookCommand.execute(request,response));
    }


}