package com.my.command.user.librarian;


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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DeleteLibrarianCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        DeleteLibrarianCommand deleteLibrarianCommand = new DeleteLibrarianCommand(userDAO);
        User user = new User();
        user.setRole(UserRole.USER);

        //when
        when(userDAO.getUserById(anyInt())).thenReturn(user);
        when(request.getParameter("userId")).thenReturn("1");

        //then
        assertEquals("account?command=librarians_settings",deleteLibrarianCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        //given
        DeleteLibrarianCommand deleteLibrarianCommand = new DeleteLibrarianCommand(userDAO);

        //when
        when(userDAO.getUserById(anyInt())).thenThrow(applicationException);
        when(request.getParameter("userId")).thenReturn("1");

        //then
        deleteLibrarianCommand.execute(request,response);
    }

}