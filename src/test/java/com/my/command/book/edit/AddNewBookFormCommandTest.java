package com.my.command.book.edit;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddNewBookFormCommandTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath(){
        //given
        AddNewBookFormCommand addNewBookFormCommand = new AddNewBookFormCommand();

        //then
        assertEquals("/WEB-INF/views/add_new_book_form.jsp",addNewBookFormCommand.execute(request,response));
    }
}