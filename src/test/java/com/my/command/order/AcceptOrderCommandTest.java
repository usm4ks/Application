package com.my.command.order;


import com.my.dao.DAOFactory;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.order.OrderDAO;
import com.my.db.DBConnector;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import com.my.services.OrderBookService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AcceptOrderCommandTest {

    @Mock
    DAOFactory daoFactory;
    @Mock
    BookOnTicketDAO bookOnTicketDAO;
    @Mock
    OrderDAO orderDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    DBConnector dbConnector;
    @Mock
    Connection connection;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        AcceptOrderCommand acceptOrderCommand = new AcceptOrderCommand();
        OrderBookService.getInstance().setUp(daoFactory,dbConnector);

        //when
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("type")).thenReturn(OrderType.ON_TICKET.getType());
        when(request.getParameter("until_date")).thenReturn("2021-06-07");
        when(dbConnector.getConnection()).thenReturn(connection);
        when(daoFactory.getOrderDAO()).thenReturn(orderDAO);
        when(daoFactory.getBookOnTicketDAO()).thenReturn(bookOnTicketDAO);

        //then
        assertEquals("account?command=show_all_orders",acceptOrderCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        AcceptOrderCommand acceptOrderCommand = new AcceptOrderCommand();

        //when
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("type")).thenReturn(OrderType.ON_TICKET.getType());
        when(request.getParameter("until_date")).thenReturn("string");

        //then
        acceptOrderCommand.execute(request,response);
    }

}