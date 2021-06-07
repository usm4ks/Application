package com.my.command.book.edit;

import com.my.dao.book.BookDAO;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditBookFormCommandTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws ApplicationException, CommandException {
        //given
        EditBookFormCommand editBookFormCommand = new EditBookFormCommand(bookDAO);

        //when
        when(bookDAO.getBookById(1)).thenReturn(new Book());
        when(request.getParameter("bookId")).thenReturn("1");

        //then
        Assert.assertEquals("/WEB-INF/views/edit_book_form.jsp",editBookFormCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws CommandException {
        //given
        EditBookFormCommand editBookFormCommand = new EditBookFormCommand(bookDAO);

        //when
        when(request.getParameter("bookId")).thenReturn("1");

        //then
        editBookFormCommand.execute(request,response);
    }
}