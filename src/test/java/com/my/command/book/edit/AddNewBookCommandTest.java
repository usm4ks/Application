package com.my.command.book.edit;

import com.my.dao.book.BookDAO;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddNewBookCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        AddNewBookCommand addNewBookCommand = new AddNewBookCommand(bookDAO);

        //when
        when(request.getParameter("year")).thenReturn("1");
        when(request.getParameter("amount")).thenReturn("2");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("book_list?command=show_all_books",addNewBookCommand.execute(request,response));
    }
}