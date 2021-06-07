package com.my.command.book.search;


 import com.my.dao.book.BookDAO;
import com.my.exception.CommandException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchBookCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws CommandException {
        //given
        SearchBookCommand searchBookCommand = new SearchBookCommand(bookDAO);

        //when
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("order_result")).thenReturn(null);

        //then
        assertEquals("/WEB-INF/views/book_list.jsp",searchBookCommand.execute(request,response));
    }
}