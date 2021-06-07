package com.my.command.book.edit;

import com.my.dao.book.BookDAO;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditBookCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;


    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        EditBookCommand editBookCommand = new EditBookCommand(bookDAO);

        //when
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("year")).thenReturn("1");
        when(request.getParameter("amount")).thenReturn("2");

        //then
        assertEquals("book_list?command=show_all_books",editBookCommand.execute(request,response));
    }

}