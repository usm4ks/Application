package com.my.command.user.authorization;

import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogInCommandTest {

    @Test
    public void executeShouldReturnPathWhenUserNull() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        LogInCommand logInCommand = new LogInCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(null);
        Assert.assertEquals("index.jsp",logInCommand.execute(request,response));
    }

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        LogInCommand logInCommand = new LogInCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        User user = new User();
        user.setPassword(DigestUtils.md5Hex("password"));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(user);
        Assert.assertEquals("book_list?command=show_all_books&page=1",logInCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        LogInCommand logInCommand = new LogInCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenThrow(mock(ApplicationException.class));
        logInCommand.execute(request,response);
    }

}