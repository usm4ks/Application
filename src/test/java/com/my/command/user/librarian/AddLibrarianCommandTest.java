package com.my.command.user.librarian;

import com.my.dao.user.UserDAO;
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
public class AddLibrarianCommandTest {

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
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        AddLibrarianCommand addLibrarianCommand = new AddLibrarianCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenReturn(null);
        when(request.getParameter("librarianEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("account?command=librarians_settings",addLibrarianCommand.execute(request,response));
    }
    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        AddLibrarianCommand addLibrarianCommand = new AddLibrarianCommand(userDAO);

        //when
        when(userDAO.getUserByEmail(anyString())).thenThrow(applicationException);
        when(request.getParameter("librarianEmail")).thenReturn("email");

        //then
        addLibrarianCommand.execute(request,response);
    }

}