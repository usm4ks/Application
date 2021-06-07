package com.my.command.order;


import com.my.dao.DAOFactory;
import com.my.dao.order.impl.OrderDAOImpl;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAllOrdersCommandTest {

    @Test
    public void executeShouldReturnPath() throws CommandException, ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getOrderDAO()).thenReturn(mock(OrderDAOImpl.class));
        when(daoFactory.getOrderDAO().getAllOrders()).thenReturn(new ArrayList<>());
        ShowAllOrdersCommand showAllOrdersCommand = new ShowAllOrdersCommand(daoFactory.getOrderDAO());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Assert.assertEquals("/WEB-INF/views/order_list.jsp",showAllOrdersCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws CommandException, ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getOrderDAO()).thenReturn(mock(OrderDAOImpl.class));
        when(daoFactory.getOrderDAO().getAllOrders()).thenThrow(new ApplicationException("exception",new SQLException()));
        ShowAllOrdersCommand showAllOrdersCommand = new ShowAllOrdersCommand(daoFactory.getOrderDAO());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        showAllOrdersCommand.execute(request,response);
    }

}