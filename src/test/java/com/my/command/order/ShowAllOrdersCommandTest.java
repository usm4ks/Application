package com.my.command.order;

import com.my.dao.order.OrderDAO;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllOrdersCommandTest {

    @Mock
    OrderDAO orderDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;

    @Test
    public void executeShouldReturnPath() throws CommandException, ApplicationException {
        //given
        ShowAllOrdersCommand showAllOrdersCommand = new ShowAllOrdersCommand(orderDAO);

        //when
        when(orderDAO.getAllOrders()).thenReturn(new ArrayList<>());

        //then
        assertEquals("/WEB-INF/views/order_list.jsp",showAllOrdersCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws CommandException, ApplicationException {
        //given
        ShowAllOrdersCommand showAllOrdersCommand = new ShowAllOrdersCommand(orderDAO);

        //when
        when(orderDAO.getAllOrders()).thenThrow(applicationException);

        //then
        showAllOrdersCommand.execute(request,response);
    }

}