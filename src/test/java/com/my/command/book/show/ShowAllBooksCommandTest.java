package com.my.command.book.show;

import com.my.command.Command;
import com.my.command.CommandConstants;
import com.my.command.CommandFactory;
import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class ShowAllBooksCommandTest {

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowExceptionIfPageNotCorrect() throws CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        CommandFactory commandFactory = new CommandFactory(daoFactory);
        Command showAllBooksCommand = commandFactory.getCommand(CommandConstants.SHOW_ALL_BOOKS);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("page")).thenReturn("abc");
        HttpServletResponse response = mock(HttpServletResponse.class);
        showAllBooksCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPath() throws CommandException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowAllBooksCommand showAllBooksCommand = new ShowAllBooksCommand(daoFactory.getBookDAO());
        when(daoFactory.getBookDAO()).thenReturn(mock(BookDAOImpl.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Assert.assertEquals("/WEB-INF/views/book_list.jsp",showAllBooksCommand.execute(request,response));
    }

}
