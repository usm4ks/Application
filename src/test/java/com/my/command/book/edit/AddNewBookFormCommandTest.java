package com.my.command.book.edit;

import com.my.command.book.show.ShowAllBooksCommand;
import com.my.dao.DAOFactory;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;


public class AddNewBookFormCommandTest {

    @Test
    public void executeShouldReturnPath(){
        DAOFactory daoFactory = mock(DAOFactory.class);
        AddNewBookFormCommand addNewBookFormCommand = new AddNewBookFormCommand(daoFactory);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Assert.assertEquals("/WEB-INF/views/add_new_book_form.jsp",addNewBookFormCommand.execute(request,response));
    }
}