package com.my.command.user.settings;

import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnblockUserCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        UnblockUserCommand unblockUserCommand = new UnblockUserCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        User user = mock(User.class);
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenReturn(user);
        when(user.isBlocked()).thenReturn(false);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        Assert.assertEquals("account?command=users_settings",unblockUserCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        UnblockUserCommand unblockUserCommand = new UnblockUserCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenThrow(mock(ApplicationException.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        unblockUserCommand.execute(request,response);
    }

}