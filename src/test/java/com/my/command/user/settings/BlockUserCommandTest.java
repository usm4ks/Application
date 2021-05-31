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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class BlockUserCommandTest {

    @Test
    public void executeShouldReturnPathIfUserNull() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        BlockUserCommand blockUserCommand = new BlockUserCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(null);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals("account?command=users_settings",blockUserCommand.execute(request,response));
    }
    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        BlockUserCommand blockUserCommand = new BlockUserCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        User user = mock(User.class);
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(user);
        when(user.getRole()).thenReturn(UserRole.USER);
        when(user.isBlocked()).thenReturn(false);
        when(user.getId()).thenReturn(1);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals("account?command=users_settings",blockUserCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        BlockUserCommand blockUserCommand = new BlockUserCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenThrow(mock(ApplicationException.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userEmail")).thenReturn("email");
        blockUserCommand.execute(request,response);
    }

}