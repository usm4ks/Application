package com.my.command.user.librarian;


import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddLibrarianCommandTest {


    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        AddLibrarianCommand addLibrarianCommand = new AddLibrarianCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenReturn(null);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("librarianEmail")).thenReturn("email");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals("account?command=librarians_settings",addLibrarianCommand.execute(request,response));
    }
    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        AddLibrarianCommand addLibrarianCommand = new AddLibrarianCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserByEmail(anyString())).thenThrow(mock(ApplicationException.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("librarianEmail")).thenReturn("email");
        addLibrarianCommand.execute(request,response);
    }

}