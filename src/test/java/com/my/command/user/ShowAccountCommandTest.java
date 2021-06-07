package com.my.command.user;

import com.my.dao.DAOFactory;
import com.my.dao.order.impl.OrderDAOImpl;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAccountCommandTest {


    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowAccountCommand showAccountCommand = new ShowAccountCommand(daoFactory.getOrderDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        User user = mock(User.class);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(UserRole.LIBRARIAN);
       Assert.assertEquals("WEB-INF/views/account.jsp",showAccountCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowAccountCommand showAccountCommand = new ShowAccountCommand(daoFactory.getOrderDAO(),daoFactory.getBookOnTicketDAO(), daoFactory.getBookInHallDAO());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        User user = mock(User.class);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(UserRole.USER);
        when(daoFactory.getOrderDAO()).thenReturn(mock(OrderDAOImpl.class));
        when(daoFactory.getOrderDAO().getUserOrders(anyInt())).thenThrow(mock(ApplicationException.class));
        showAccountCommand.execute(request,response);
    }

}