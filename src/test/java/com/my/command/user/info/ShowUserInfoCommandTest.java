package com.my.command.user.info;


import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
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

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowUserInfoCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    BookOnTicketDAO bookOnTicketDAO;
    @Mock
    BookInHallDAO bookInHallDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;


    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws ApplicationException, CommandException {
        //given
        ShowUserInfoCommand showUserInfoCommand = new ShowUserInfoCommand(userDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(userDAO.getUserById(anyInt())).thenThrow(applicationException);
        when(request.getParameter("userId")).thenReturn("1");

        //then
        showUserInfoCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPath() throws ApplicationException,CommandException {
        //given
        ShowUserInfoCommand showUserInfoCommand = new ShowUserInfoCommand(userDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(userDAO.getUserById(anyInt())).thenReturn(new User());
        when(bookOnTicketDAO.getAllBooksOnUserTicket(anyInt())).thenReturn(new ArrayList<>());
        when(bookInHallDAO.getAllUserBooksInHall(anyInt())).thenReturn(new ArrayList<>());
        when(request.getParameter("userId")).thenReturn("1");

        //then
        assertEquals("/WEB-INF/views/user_info.jsp",showUserInfoCommand.execute(request,response));
    }

}