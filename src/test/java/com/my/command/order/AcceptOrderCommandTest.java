package com.my.command.order;


import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookOnTicketDAOImpl;
import com.my.dao.order.impl.OrderDAOImpl;
import com.my.db.DBConnector;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import com.my.services.OrderBookService;
import org.junit.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import static org.mockito.Mockito.*;

public class AcceptOrderCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        AcceptOrderCommand acceptOrderCommand = new AcceptOrderCommand();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("type")).thenReturn(OrderType.ON_TICKET.getType());
        when(request.getParameter("until_date")).thenReturn("2021-06-07");
        DBConnector dbConnector = mock(DBConnector.class);
        Connection connection = mock(Connection.class);
        OrderBookService.getInstance().setUp(daoFactory,dbConnector);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(daoFactory.getOrderDAO()).thenReturn(mock(OrderDAOImpl.class));
        when(daoFactory.getBookOnTicketDAO()).thenReturn(mock(BookOnTicketDAOImpl.class));
        Assert.assertEquals("account?command=show_all_orders",acceptOrderCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        AcceptOrderCommand acceptOrderCommand = new AcceptOrderCommand();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("type")).thenReturn(OrderType.ON_TICKET.getType());
        when(request.getParameter("until_date")).thenReturn("string");
        acceptOrderCommand.execute(request,response);
    }

}