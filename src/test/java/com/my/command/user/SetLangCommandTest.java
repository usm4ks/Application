package com.my.command.user;

import com.my.dao.DAOFactory;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SetLangCommandTest {

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("lang")).thenReturn("ru");
        SetLangCommand setLangCommand = new SetLangCommand(daoFactory);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("user")).thenReturn(mock(User.class));
        Assert.assertEquals("book_list?command=show_all_books",setLangCommand.execute(request,response));
    }

    @Test
    public void executeShouldReturnPathIfUserNull() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("lang")).thenReturn("ru");
        SetLangCommand setLangCommand = new SetLangCommand(daoFactory);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("user")).thenReturn(null);
        Assert.assertEquals("index.jsp",setLangCommand.execute(request,response));
    }

}