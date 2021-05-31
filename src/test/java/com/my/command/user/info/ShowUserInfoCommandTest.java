package com.my.command.user.info;


import com.my.command.book.edit.EditBookFormCommand;
import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.dao.book.impl.BookInHallDAOImpl;
import com.my.dao.book.impl.BookOnTicketDAOImpl;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.entities.Book;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowUserInfoCommandTest {


    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowUserInfoCommand showUserInfoCommand = new ShowUserInfoCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenThrow(ApplicationException.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        showUserInfoCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowUserInfoCommand showUserInfoCommand = new ShowUserInfoCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getUserById(anyInt())).thenReturn(mock(User.class));
        when(daoFactory.getBookOnTicketDAO()).thenReturn(mock(BookOnTicketDAOImpl.class));
        when(daoFactory.getBookOnTicketDAO().getAllBooksOnUserTicket(anyInt())).thenReturn(new ArrayList<>());
        when(daoFactory.getBookInHallDAO()).thenReturn(mock(BookInHallDAOImpl.class));
        when(daoFactory.getBookInHallDAO().getAllUserBooksInHall(anyInt())).thenReturn(new ArrayList<>());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("userId")).thenReturn("1");
        Assert.assertEquals("/WEB-INF/views/user_info.jsp",showUserInfoCommand.execute(request,response));
    }

}