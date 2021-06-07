package com.my.command.user;

import com.my.entities.User;
import com.my.exception.ApplicationException;
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
public class SetLangCommandTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        //given
        SetLangCommand setLangCommand = new SetLangCommand();
        User user = new User();

        //when
        when(request.getParameter("lang")).thenReturn("ru");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);

        //then
        assertEquals("book_list?command=show_all_books",setLangCommand.execute(request,response));
    }

    @Test
    public void executeShouldReturnPathIfUserNull() {
        //given
        SetLangCommand setLangCommand = new SetLangCommand();

        //when
        when(request.getParameter("lang")).thenReturn("ru");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(null);

        //then
        assertEquals("index.jsp",setLangCommand.execute(request,response));
    }

}