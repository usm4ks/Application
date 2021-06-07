package com.my.command.book.show;

import com.my.dao.book.BookDAO;
import com.my.exception.CommandException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllBooksCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test(expected = CommandException.class)
    public void executeShouldThrowExceptionIfPageNotCorrect() throws CommandException {
        //given
        ShowAllBooksCommand showAllBooksCommand = new ShowAllBooksCommand(bookDAO);

        //when
        when(request.getParameter("page")).thenReturn("abc");

        //then
        showAllBooksCommand.execute(request,response);
    }

    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        ShowAllBooksCommand showAllBooksCommand = new ShowAllBooksCommand(bookDAO);

        //then
        assertEquals("/WEB-INF/views/book_list.jsp",showAllBooksCommand.execute(request,response));
    }

}
