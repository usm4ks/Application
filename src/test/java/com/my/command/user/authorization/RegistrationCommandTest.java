package com.my.command.user.authorization;

import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        RegistrationCommand registrationCommand = new RegistrationCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(mock(User.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("firstName")).thenReturn(anyString());
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals("registration",registrationCommand.execute(request,response));
    }
    @Test
    public void executeShouldReturnPathIfUserNull() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        RegistrationCommand registrationCommand = new RegistrationCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(null);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("firstName")).thenReturn(anyString());
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals("book_list?command=show_all_books&page=1",registrationCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        RegistrationCommand registrationCommand = new RegistrationCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("abc");
        registrationCommand.execute(request,response);
    }

}