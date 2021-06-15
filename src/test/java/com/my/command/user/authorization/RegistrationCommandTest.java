package com.my.command.user.authorization;

import com.my.dao.user.UserDAO;
import com.my.entities.User;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    User user;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        RegistrationCommand registrationCommand = new RegistrationCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenReturn(user);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("firstName")).thenReturn(anyString());
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("registration",registrationCommand.execute(request,response));
    }
    @Test
    public void executeShouldReturnPathIfUserNull() throws ApplicationException,CommandException {
        //given
        RegistrationCommand registrationCommand = new RegistrationCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenReturn(null);
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("firstName")).thenReturn(anyString());
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("book_list?command=show_all_books",registrationCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        RegistrationCommand registrationCommand = new RegistrationCommand(userDAO);

        //when
        when(request.getParameter("email")).thenReturn(anyString());
        when(request.getParameter("password")).thenReturn("abc");

        //then
        registrationCommand.execute(request,response);
    }

}