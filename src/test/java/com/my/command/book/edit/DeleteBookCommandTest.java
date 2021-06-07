package com.my.command.book.edit;

import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.entities.Book;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBookCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    BookOnTicketDAO bookOnTicketDAO;
    @Mock
    BookInHallDAO bookInHallDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(bookDAO.getBookById(1)).thenReturn(new Book());
        when(bookInHallDAO.getBookInHallByBookId(1)).thenReturn(new ArrayList<>());
        when(bookOnTicketDAO.getBookOnTicketByBookId(1)).thenReturn(new ArrayList<>());
        when(request.getParameter("bookId")).thenReturn("1");

        //then
        assertEquals("book_list?command=show_all_books",deleteBookCommand.execute(request,response));
    }

}