package com.my.command.user.settings;

import com.my.dao.user.UserDAO;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BlockUserCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;

    @Test
    public void executeShouldReturnPathIfUserNull() throws ApplicationException, CommandException {
        //given
        BlockUserCommand blockUserCommand = new BlockUserCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenReturn(null);
        when(request.getParameter("userEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("account?command=users_settings",blockUserCommand.execute(request,response));
    }
    @Test
    public void executeShouldReturnPath() throws ApplicationException,CommandException {
        //given
        BlockUserCommand blockUserCommand = new BlockUserCommand(userDAO);
        User user = new User();
        user.setRole(UserRole.USER);
        user.setBlocked(false);
        user.setId(1);

        //when
        when(userDAO.getUserByEmail(anyString())).thenReturn(user);
        when(request.getParameter("userEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("account?command=users_settings",blockUserCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        BlockUserCommand blockUserCommand = new BlockUserCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenThrow(applicationException);
        when(request.getParameter("userEmail")).thenReturn("email");

        //then
        blockUserCommand.execute(request,response);
    }

}