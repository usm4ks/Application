package com.my.command.user;

import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.order.OrderDAO;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAccountCommandTest {

    @Mock
    OrderDAO orderDAO;
    @Mock
    BookOnTicketDAO bookOnTicketDAO;
    @Mock
    BookInHallDAO bookInHallDAO;
    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;


    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        ShowAccountCommand showAccountCommand = new ShowAccountCommand(orderDAO,bookOnTicketDAO,bookInHallDAO);
        User user = new User();
        user.setRole(UserRole.LIBRARIAN);

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);

        //then
        assertEquals("WEB-INF/views/account.jsp",showAccountCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        ShowAccountCommand showAccountCommand = new ShowAccountCommand(orderDAO,bookOnTicketDAO,bookInHallDAO);
        User user = new User();
        user.setRole(UserRole.USER);

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(orderDAO.getUserOrders(anyInt())).thenThrow(applicationException);

        //then
        showAccountCommand.execute(request,response);
    }

}