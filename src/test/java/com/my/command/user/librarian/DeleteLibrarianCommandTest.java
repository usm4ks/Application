package com.my.command.user.librarian;


import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteLibrarianCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        DeleteLibrarianCommand deleteLibrarianCommand = new DeleteLibrarianCommand(daoFactory.getUserDAO());
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        User user = mock(User.class);
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenReturn(user);
        when(user.getRole()).thenReturn(UserRole.USER);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        Assert.assertEquals("account?command=librarians_settings",deleteLibrarianCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException,CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        DeleteLibrarianCommand deleteLibrarianCommand = new DeleteLibrarianCommand(daoFactory.getUserDAO());
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenThrow(mock(ApplicationException.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        deleteLibrarianCommand.execute(request,response);
    }

}