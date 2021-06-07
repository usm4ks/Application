package com.my.command.user.authorization;

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
public class LogOutCommandTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        //given
        LogOutCommand logOutCommand = new LogOutCommand();

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(null);

        //then
        assertEquals("index.jsp",logOutCommand.execute(request,response));
    }

}