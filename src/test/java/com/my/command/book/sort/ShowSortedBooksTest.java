package com.my.command.book.sort;

import com.my.dao.book.BookDAO;
import com.my.exception.CommandException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowSortedBooksTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        ShowSortedBooks showSortedBooks = new ShowSortedBooks(bookDAO);

        //when
        when(request.getParameter("order_result")).thenReturn(null);

        //then
        assertEquals("/WEB-INF/views/book_list.jsp",showSortedBooks.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws CommandException {
        //given
        ShowSortedBooks showSortedBooks = new ShowSortedBooks(bookDAO);

        //when
        when(request.getParameter("page")).thenReturn("abc");

        //then
        showSortedBooks.execute(request,response);
    }

}