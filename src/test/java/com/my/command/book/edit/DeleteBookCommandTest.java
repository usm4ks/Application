package com.my.command.book.edit;

import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.entities.Book;
import com.my.entities.BookOnTicket;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
    @Mock
    HttpSession session;
    @Mock
    List<BookOnTicket> bookOnTicketList;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(bookDAO.getBookById(1)).thenReturn(new Book());
        when(bookInHallDAO.getBookInHallByBookId(1)).thenReturn(new ArrayList<>());
        when(bookOnTicketDAO.getBookOnTicketByBookId(1)).thenReturn(new ArrayList<>());
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        //then
        assertEquals("book_list?command=show_all_books",deleteBookCommand.execute(request,response));
    }

    public void shouldSetAttributeIfBookCantBeDeleted() throws ApplicationException, CommandException {
        //given
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(bookDAO.getBookById(1)).thenReturn(new Book());
        when(bookInHallDAO.getBookInHallByBookId(1)).thenReturn(new ArrayList<>());
        when(bookOnTicketDAO.getBookOnTicketByBookId(1)).thenReturn(bookOnTicketList);
        when(bookOnTicketList.isEmpty()).thenReturn(false);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        //then
        deleteBookCommand.execute(request,response);
        verify(session).setAttribute("delete_result","book_cant_be_deleted");
    }

    public void shouldSetAttributeIfBookDeleted() throws ApplicationException, CommandException {
        //given
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookDAO,bookOnTicketDAO,bookInHallDAO);

        //when
        when(bookDAO.getBookById(1)).thenReturn(new Book());
        when(bookInHallDAO.getBookInHallByBookId(1)).thenReturn(new ArrayList<>());
        when(bookOnTicketDAO.getBookOnTicketByBookId(1)).thenReturn(bookOnTicketList);
        when(bookOnTicketList.isEmpty()).thenReturn(true);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        //then
        deleteBookCommand.execute(request,response);
        verify(session).setAttribute("delete_result","book_was_deleted");
    }

}